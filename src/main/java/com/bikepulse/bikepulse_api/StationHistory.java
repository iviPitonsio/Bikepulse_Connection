package com.bikepulse.bikepulse_api;

import java.sql.Timestamp;

public class StationHistory {
	
	// Atributos
		private String name;
		private int idEstacion;
		private Timestamp fecha;
		private int bicisDisponibles;
		
		// Constructor
		public StationHistory() {
			
		}
		
		// Getters
		public String getName() {
			return name;
		}
		
		public int getIdEstacion() {
			return idEstacion;
		}

		public Timestamp getFecha() {
			return fecha;
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

		public void setFecha(Timestamp fecha) {
			this.fecha = fecha;
		}

		public void setNumVehiclesAvailable(int bicisDisponibles) {
			this.bicisDisponibles = bicisDisponibles;
		}

}
