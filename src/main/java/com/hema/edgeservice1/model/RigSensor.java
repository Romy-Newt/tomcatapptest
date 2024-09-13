package com.hema.edgeservice1.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RigSensor 
{
	@Id
    private int SensorId;
    private String SensorName;
    private String SensorModel;
    private String Type; 
    private String TemperatureValue;
    private LocalDateTime SensorDateTime;
    
    public RigSensor() {
		
	}

	public int getSensorId() {
		return SensorId;
	}
	public void setSensorId(int sensorId) {
		SensorId = sensorId;
	}
	public String getSensorName() {
		return SensorName;
	}
	public void setSensorName(String sensorName) {
		SensorName = sensorName;
	}
	public String getSensorModel() {
		return SensorModel;
	}
	public void setSensorModel(String sensorModel) {
		SensorModel = sensorModel;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getTemperatureValue() {
		return TemperatureValue;
	}
	public void setTemperatureValue(String temperatureValue) {
		TemperatureValue = temperatureValue;
	}
	public LocalDateTime getSensorDateTime() {
		return SensorDateTime;
	}
	public void setSensorDateTime(LocalDateTime sensorDateTime) {
		SensorDateTime = sensorDateTime;
	}
	
	@Override
	public String toString() {
		return "RigSensor [SensorId=" + SensorId + ", SensorName=" + SensorName + ", SensorModel=" + SensorModel
				+ ", Type=" + Type + ", TemperatureValue=" + TemperatureValue + ", SensorDateTime=" + SensorDateTime
				+ "]";
	}

	public RigSensor(int sensorId, String sensorName, String sensorModel, String type, String temperatureValue,
			LocalDateTime sensorDateTime) {
		super();
		SensorId = sensorId;
		SensorName = sensorName;
		SensorModel = sensorModel;
		Type = type;
		TemperatureValue = temperatureValue;
		SensorDateTime = sensorDateTime;
	}
	
	
    
    
}
