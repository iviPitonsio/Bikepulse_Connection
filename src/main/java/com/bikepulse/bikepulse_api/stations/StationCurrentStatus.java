package com.bikepulse.bikepulse_api.stations;

import java.sql.Timestamp;

public class StationCurrentStatus {
	
	private String name;
	private int stationId;
	private int availableBikes;
	private int availableDocks;
	private int capacity;
	private boolean renting;
	private boolean returning;
	private Timestamp date;
	
	public StationCurrentStatus() {
		
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public int getStationId() {
		return stationId;
	}
	
	public int getAvailableBikes() {
		return availableBikes;
	}
	
	public int getAvailableDocks() {
		return availableDocks;
	}
	
	public int getCapacity() {
		return capacity;
	}
	

	public boolean isRenting() {
		return renting;
	}
	

	public boolean isReturning() {
		return returning;
	}
	
	public Timestamp getDate() {
		return date;
	}
	
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public void setVehiclesAvailable(int availableBikes) {
		this.availableBikes = availableBikes;
	}
	
	public void setDocksAvailable(int availableDocks) {
		this.availableDocks = availableDocks;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public void setRenting(boolean renting) {
		this.renting = renting;
	}

	public void setReturning(boolean returning) {
		this.returning = returning;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
