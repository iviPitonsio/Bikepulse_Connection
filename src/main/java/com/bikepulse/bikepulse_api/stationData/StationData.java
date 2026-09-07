package com.bikepulse.bikepulse_api.stationData;

import java.util.List;

/** Clase contenedor que modela el contenido del campo "data" de la API, el cual contiene el array con todas las estaciones
 * Sirve para Station como para StationStatus*/
public class StationData<T> {
	
	private List<T> stations; // Declaracion de atributo
	
	public StationData() { // Constructor
		
	}
	
	//Getter
	
	public List<T> getStations(){
		return stations;
	}
	
	//Setter
	
	public void setStations(List<T> stations) {
		this.stations = stations;
	}

}
