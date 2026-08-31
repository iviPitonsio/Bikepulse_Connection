package com.bikepulse.bikepulse_api.stations;

import java.sql.Timestamp;

public class StationHistory {
	
	// Atributos
		private String name;
		private int stationId;
		private Timestamp date;
		private int availableBikes;
		
		// Constructor
		public StationHistory() {
			
		}
		
		// Getters
		public String getName() {
			return name;
		}
		
		public int getStationId() {
			return stationId;
		}

		public Timestamp getDate() {
			return date;
		}
		
		public int getAvailableBikes() {
			return availableBikes;
		}
		
		// Setters
		public void setName(String name) {
			this.name = name;
		}

		public void setStationId(int StationId) {
			this.stationId = StationId;
		}

		public void setDate(Timestamp date) {
			this.date = date;
		}

		public void setNumVehiclesAvailable(int availableBikes) {
			this.availableBikes = availableBikes;
		}

}
