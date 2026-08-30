package com.bikepulse.bikepulse_api;

import station.Station;
import station.StationStatus;

/** Clase que contiene los datos de Station y StationStatus, util para devolver datos que conlleven cruzar ambas tablas*/
public class StationData {
	
	// Atributos
	private Station station;
	private StationStatus stationStatus;
	
	// Constructor
	public StationData() {
		
	}
	
	// Getters
	
	public Station getStation() {
		return station;
	}
	
	public StationStatus getStatus() {
		return stationStatus;
	}
	
	// Setters
	
	public void setStation(Station station) {
		this.station = station;
	}
	
	public void setStatus(StationStatus status) {
		this.stationStatus = status;
	}
	
}
