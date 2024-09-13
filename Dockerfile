# Stage 1: Build the application
FROM openjdk:21-jdk-slim AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Project
COPY . .

# Install Maven and build the application
RUN apt-get update && apt-get install -y maven \
    && mvn clean package -DskipTests

# Stage 2: Set up Tomcat and deploy the WAR
FROM openjdk:21-jdk-slim

# Define Tomcat version
ARG TOMCAT_VERSION=10.1.28

# Download and setup Tomcat
RUN apt-get update && \
    apt-get install -y wget && \
    wget https://archive.apache.org/dist/tomcat/tomcat-10/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    tar -xzvf apache-tomcat-${TOMCAT_VERSION}.tar.gz -C /opt && \
    rm apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    ln -s /opt/apache-tomcat-${TOMCAT_VERSION} /opt/tomcat && \
    apt-get purge -y wget && \
    apt-get autoremove -y && \
    rm -rf /var/lib/apt/lists/*

# Set environment variables for Tomcat
ENV CATALINA_HOME /opt/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH

# Create Tomcat user and group, set ownership and permissions for Tomcat directories
RUN groupadd -r tomcat && \
    useradd -r -g tomcat -d $CATALINA_HOME -s /sbin/nologin tomcat && \
    chown -R tomcat:tomcat $CATALINA_HOME && \
    chmod -R 755 $CATALINA_HOME

# Copy the WAR file from the build stage
COPY --from=builder /app/target/*.war $CATALINA_HOME/webapps/myapp.war

# Set permissions for the deployed WAR
RUN chmod 755 $CATALINA_HOME/webapps/myapp.war

# Expose the port Tomcat runs on
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
