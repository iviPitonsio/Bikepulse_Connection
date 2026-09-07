package com.bikepulse.bikepulse_api.stationData;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Clase que contiene todos los datos de Station*/
@JsonIgnoreProperties(value = { "vehicle_types_capacity", "vehicle_docks_capacity"}, ignoreUnknown = true) // Json ignora todo lo que no reconozca
public class Station {
	
	// Atributos
	@JsonProperty("station_id")
	private int stationId;
	
	@JsonProperty("external_id")
	private String externalId;
	
	@JsonProperty("lat")
	private double lat;
	
	@JsonProperty("lon")
	private double lon;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("post_code")
	private String postCode;
	
	@JsonProperty("capacity")
	private int capacity;
	
	@JsonProperty("is_charging_station")
	private boolean isChargingStation;
	
	@JsonProperty("is_virtual_station")
	private boolean isVirtualStation;
	
	@JsonProperty("name")
	private List<LocalizedName> names;
	
	
	// Constructor
	public Station () {
		
	}
	
	// Getters
	public int getStationId() {
		return stationId;
	}
	
	public String getExternalId() {
		return externalId;
	}
	
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPostCode() {
		return postCode;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	@JsonIgnore // Json ignorara este campo para evitar atributos repetidos
	public boolean isChargingStation() {
		return isChargingStation;
	}
	
	@JsonIgnore
	public boolean isVirtualStation() {
		return isVirtualStation;
	}
	
	@JsonIgnore
	public List<LocalizedName> getNames() {
		return names;
	}
	
	// Setters
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
		
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
		
	public void setLat(double lat) {
		this.lat = lat;
	}
		
	public void setLon(double lon) {
		this.lon = lon;
	}
		
	public void setAddress(String address) {
		this.address = address;
	}
		
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
		
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
		
	public void setIsChargingStation(boolean isChargingStation) {
		this.isChargingStation = isChargingStation;
	}
		
	public void setIsVirtualStation(boolean isVirtualStation) {
		this.isVirtualStation = isVirtualStation;
	}
	
	public void setNames(List<LocalizedName> names) {
		this.names = names;
	}
	
}
