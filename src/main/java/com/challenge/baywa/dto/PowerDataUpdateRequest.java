package com.challenge.baywa.dto;

import java.io.Serializable;

public class PowerDataUpdateRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String windPark;
	private String period;
	private double powerProduced;
	private String timeStamp;
	
	public PowerDataUpdateRequest() {
		
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

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "PowerDataUpdateRequest [windPark=" + windPark + ", period=" + period + ", powerProduced="
				+ powerProduced + ", timeStamp=" + timeStamp + "]";
	}
}
