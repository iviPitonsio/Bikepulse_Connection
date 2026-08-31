package com.bikepulse.bikepulse_api.stations;

public class StationSummary {
	
	private int totalStations;
	private int activeStations;
	private int bikesAvailable;
	private int docksAvailable;
	private int totalCapacity;
	
	public StationSummary() {
		
	}
	
	// Getters

	public int getTotalStations() {
		return totalStations;
	}
	
	public int getActiveStations() {
		return activeStations;
	}
	
	public int getBikesAvailable() {
		return bikesAvailable;
	}
	
	public int getDocksAvailable() {
		return docksAvailable;
	}
	
	public int getTotalCapacity() {
		return totalCapacity;
	}
	
	// Setters

	public void setTotalStations(int totalStations) {
		this.totalStations = totalStations;
	}

	public void setActiveStations(int activeStations) {
		this.activeStations = activeStations;
	}

	public void setBikesAvailable(int bikesAvailable) {
		this.bikesAvailable = bikesAvailable;
	}
	

	public void setDocksAvailable(int docksAvailable) {
		this.docksAvailable = docksAvailable;
	}


	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}
		
	
}
