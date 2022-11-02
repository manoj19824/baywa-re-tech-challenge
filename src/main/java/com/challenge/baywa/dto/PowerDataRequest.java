package com.challenge.baywa.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PowerDataRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String windPark;
	private String period;
	private double powerProduced;
	private String timestamp;
	private String createdOn;
	private String updatedOn;
	
	public PowerDataRequest() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWindPark() {
		return windPark;
	}
	public void setWindPark(String windPark) {
		this.windPark = windPark;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public double getPowerProduced() {
		return powerProduced;
	}
	public void setPowerProduced(double powerProduced) {
		this.powerProduced = powerProduced;
	}
	
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "PowerDataRequest [id=" + id + ", windPark=" + windPark + ", period=" + period + ", powerProduced="
				+ powerProduced + ", timestamp=" + timestamp + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn
				+ "]";
	}
	
	
}
