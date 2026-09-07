package com.bikepulse.bikepulse_api.stationData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Clase contendor que nos devuelve los primeros 3 atributos de la API (tanto de Status como de Station)*/
@JsonIgnoreProperties(ignoreUnknown = true) // Json ignora todo lo que no reconozca
public class StationResponse<T> {
	
	// Atributos
	
	@JsonProperty("last_updated") // Propiedad que relaciona el campo escrito en la API con el que le damos nosotros
	private String lastUpdated;
	
	private int ttl;
	
	private StationData<T> data;
	
	
	// Constructor
	
	public StationResponse() { 
		
	}
	
	//Getters
	
	public String getLastUpdated() {
		return lastUpdated;
	}
	
	public int getTtl() {
		return ttl;
	}
	
	public StationData<T> getData() {
		return data;
	}
	
	// Setters
	
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}
	
	public void setData(StationData<T> data) {
		this.data = data;
	}
}
