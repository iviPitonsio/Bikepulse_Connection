package com.bikepulse.bikepulse_api.stations;

/** Clase que contiene los datos concretos para ejecutar las consultas relacionadas con las estaciones llenas y vacias*/
public class StationBikes {

	// Atributos
	private String name;
	private int stationId;
	private int capacity;
	private int availableBikes;
	
	// Constructor
	public StationBikes() {
		
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public int getEstationId() {
		return stationId;
	}

	public int getCapacity() {
		return capacity;
	}
	
	public int getAvailableBikes() {
		return availableBikes;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public void setCapacity(int capacidad) {
		this.capacity = capacidad;
	}

	public void setVehiclesAvailable(int bicisDisponibles) {
		this.availableBikes = bicisDisponibles;
	}
	
}
