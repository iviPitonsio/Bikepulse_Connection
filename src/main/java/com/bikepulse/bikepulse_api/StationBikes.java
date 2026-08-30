package com.bikepulse.bikepulse_api;

/** Clase que contiene los datos concretos para ejecutar las consultas relacionadas con las estaciones llenas y vacias*/
public class StationBikes {

	// Atributos
	private String name;
	private int idEstacion;
	private int capacidad;
	private int bicisDisponibles;
	
	// Constructor
	public StationBikes() {
		
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public int getIdEstacion() {
		return idEstacion;
	}

	public int getCapacidad() {
		return capacidad;
	}
	
	public int getBicisDisponibles() {
		return bicisDisponibles;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setStationId(int StationId) {
		this.idEstacion = StationId;
	}

	public void setCapacity(int capacidad) {
		this.capacidad = capacidad;
	}

	public void setNumVehiclesAvailable(int bicisDisponibles) {
		this.bicisDisponibles = bicisDisponibles;
	}
	
}
