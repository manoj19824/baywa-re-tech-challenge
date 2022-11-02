package com.challenge.baywa.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class PowerDataEntity {

	@NotNull
    @Id
    private String id;

	private String windPark;

	private double powerProduced;

	private String period;

	private Instant timestamp;

	private Instant createdOn;

	private Instant updatedOn;

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

	public double getPowerProduced() {
		return powerProduced;
	}

	public void setPowerProduced(double powerProduced) {
		this.powerProduced = powerProduced;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Instant getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

	public Instant getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Instant updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "PowerDataEntity [id=" + id + ", windPark=" + windPark + ", powerProduced=" + powerProduced + ", period="
				+ period + ", timestamp=" + timestamp + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}
	
}
