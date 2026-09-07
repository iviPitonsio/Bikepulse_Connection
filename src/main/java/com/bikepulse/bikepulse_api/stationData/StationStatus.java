package com.bikepulse.bikepulse_api.stationData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignoramos todo lo que Json no identifique
public class StationStatus {
	
	//Declaracion de atributos
	
	@JsonProperty("station_id")
	private int stationId;
	
	@JsonProperty("num_vehicles_available")
	private int numVehiclesAvailable;
	
	@JsonProperty("num_vehicles_disabled")
	private int numVehiclesDisabled;
	
	@JsonProperty("num_docks_available")
	private int numDocksAvailable;
	
	@JsonProperty("num_docks_disabled")
	private int numDocksDisabled;
	
	@JsonProperty("last_reported")
	private String lastReported;
	
	@JsonProperty("is_installed")
	private boolean isInstalled;
	
	@JsonProperty("is_renting")
	private boolean isRenting;
	
	@JsonProperty("is_returning")
	private boolean isReturning;
	
	
	// Constructor
	public StationStatus() {
		
	}
	
	//Getters
	
	public int getStationId() {
		return stationId;
	}
	
	public int getNumVehiclesAvailable() {
		return numVehiclesAvailable;
	}
	
	public int getNumVehiclesDisabled() {
		return numVehiclesDisabled;
	}
	
	public int getNumDocksAvailable() {
		return numDocksAvailable;
	}
	
	public int getNumDocksDisabled() {
		return numDocksDisabled;
	}
	
	public String getLastReported() {
		return lastReported;
	}
	
	@JsonIgnore // Json ignorara este campo para evitar atributos repetidos
	public boolean isInstalled() {
		return isInstalled;
	}
	
	@JsonIgnore
	public boolean isRenting() {
		return isRenting;
	}
	
	@JsonIgnore
	public boolean isReturning() {
		return isReturning;
	}
	
	// Setters

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}	

	public void setNumVehiclesAvailable(int numVehiclesAvailable) {
		this.numVehiclesAvailable = numVehiclesAvailable;
	}

	public void setNumVehiclesDisabled(int numVehiclesDisabled) {
		this.numVehiclesDisabled = numVehiclesDisabled;
	}

	public void setNumDocksAvailable(int numDocksAvailable) {
		this.numDocksAvailable = numDocksAvailable;
	}

	public void setNumDocksDisabled(int numDocksDisabled) {
		this.numDocksDisabled = numDocksDisabled;
	}
	
	public void setLastReported(String lastReported) {
		this.lastReported = lastReported;
	}

	public void setInstalled(boolean isInstalled) {
		this.isInstalled = isInstalled;
	}

	public void setRenting(boolean isRenting) {
		this.isRenting = isRenting;
	}

	public void setReturning(boolean isReturning) {
		this.isReturning = isReturning;
	}


}
