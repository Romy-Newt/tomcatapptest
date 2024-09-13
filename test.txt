# Use Ubuntu as the base image
FROM ubuntu:22.04

# Avoid prompts from apt
ENV DEBIAN_FRONTEND=noninteractive

# Set MySQL root password 
ENV MYSQL_ROOT_PASSWORD=Romys@123
ENV MYSQL_DATABASE=RigSensor
ENV MYSQL_USER=appuser
ENV MYSQL_PASSWORD=Romys@123

# Create /nonexistent directory
RUN mkdir /nonexistent && chmod 755 /nonexistent

# Install OpenJDK for Java, Maven for building the app, MySQL for the database, and Supervisor for process control
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven mysql-server supervisor expect && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get clean


# Copy your application source
WORKDIR /app
COPY src /app/src
COPY pom.xml /app/


# Build your Spring Boot application
RUN mvn clean package -DskipTests

# Create the directory before moving the JAR
RUN mkdir -p /opt/app

# Move the built jar to a known location
RUN mv target/*.jar /opt/app/app.jar

# Copy the Supervisor configuration file
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# Copy entrypoint script
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Expose ports for your application and MySQL
EXPOSE 5050
EXPOSE 3306

# Run entrypoint script
CMD ["/entrypoint.sh"]
