package com.bikepulse.bikepulse_api.stationData;

/** Clase que contiene los nombres y los idiomas de cada uno, de cada estacion */
public class LocalizedName {
	
	// Atributos
	private String text;
	
	private String language;
	
	// Constructor
	public LocalizedName() {
		
	}
	
	//Getters
	
	public String getText() {
		return text;
	}
	
	public String getLanguage() {
		return language;
	}
	
	//Setters
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
}
