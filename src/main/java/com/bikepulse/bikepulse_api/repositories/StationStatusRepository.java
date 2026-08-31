package com.bikepulse.bikepulse_api.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import station.StationStatus;

@Repository
public class StationStatusRepository {
	
	private DataSource database;
	
	// Constructor para establecer la conexión con MySQL (lo crea Spring automaticamente a partir de la informacion de aplication.properties
	public StationStatusRepository(DataSource datasource) {
		
		database = datasource;
		
	}
	
	
	/** Funcion que devuelve los datos del estado de una estacion en concreto*/
	public List<StationStatus> stationStatus (int stationId){
		
		List<StationStatus> statuses = new ArrayList<StationStatus>(); // creamos la lista con todos los datos de cada estado de las estaciones
		
		try(Connection stationConnection = database.getConnection();
				PreparedStatement getStatus = stationConnection.prepareStatement 
					("select fecha, bicis_averiadas, bicis_disponibles, huecos_averiados, huecos_disponibles, esta_alquilando, esta_instalada, esta_devolviendo, id_estacion from registro_estacion where id_estacion = ?;")){ // escribimos la consulta
					
			getStatus.setInt(1, stationId); // Asignamos al where de la consulta el id concreto
			
			ResultSet result = getStatus.executeQuery(); // hacemos la consulta a SQL
			
			while(result.next()) {// Guardamos cada dato de la estacion en un objeto StationStatus y lo añadimos a la lista
				StationStatus status = new StationStatus();
				
				status.setLastReported(result.getString("fecha"));
				status.setNumVehiclesDisabled(result.getInt("bicis_averiadas"));
				status.setNumVehiclesAvailable(result.getInt("bicis_disponibles"));
				status.setNumDocksDisabled(result.getInt("huecos_averiados"));
				status.setNumDocksAvailable(result.getInt("huecos_disponibles"));
				status.setRenting(result.getBoolean("esta_alquilando"));
				status.setInstalled(result.getBoolean("esta_instalada"));
				status.setReturning(result.getBoolean("esta_devolviendo"));
				status.setStationId(result.getInt("id_estacion"));
				
			    statuses.add(status);
			}
			
			 
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		 return statuses;
		 
	}

}
