package com.bikepulse.bikepulse_api.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bikepulse.bikepulse_api.stationData.Station;
import com.bikepulse.bikepulse_api.stationData.StationStatus;

@Repository
public class StationStatusDatabase {
	
	private DatabaseConnection database;
	
	public StationStatusDatabase(DatabaseConnection database) {
		this.database = database; // Nos conectamos a la base de datos
	}
	
	/** Funcion que inserta los datos en la tabla registro_estacion*/
	public void StatusConnection(List<Station> stations, Map <Integer, StationStatus> statusMap) {
		
		
		// Preparamos la conexion y la consulta

			try(Connection statusConection = database.getConnection()){
			
				
				try(PreparedStatement insertStatus = statusConection.prepareStatement
					("insert into registro_estacion (id_estacion, fecha, bicis_averiadas, bicis_disponibles, huecos_averiados, huecos_disponibles, esta_alquilando, esta_instalada, esta_devolviendo) values(?, ?, ?, ?, ?, ?, ?, ?, ?);")
					){
			
			
			for(Station i: stations) {
				StationStatus status = statusMap.get(i.getStationId()); // Buscamos el dato de stationStatus que se corresponda con el ID de la estacion
				
				if(status != null) { // Comprobamos que haya datos
						
					// Convertimos la fecha a Timestamp
					Instant instant = Instant.parse(status.getLastReported());
					ZoneId zone = ZoneId.systemDefault(); 
					LocalDateTime date = LocalDateTime.ofInstant(instant, zone);
					Timestamp timestamp = Timestamp.valueOf(date);
					
					// Comprobamos si los datos estan insertados, si no lo estan, los insertamos
					if(!statusExists(statusConection, i.getStationId(), timestamp)) {
							
						//Insertamos los datos en el orden en el que estan los datos en la tabla(indice del elemento de la consulta, dato)
						insertStatus.setInt(1, status.getStationId());
						insertStatus.setTimestamp(2, timestamp);
						insertStatus.setInt(3, status.getNumVehiclesDisabled());
						insertStatus.setInt(4, status.getNumVehiclesAvailable());
						insertStatus.setInt(5, status.getNumDocksDisabled());
						insertStatus.setInt(6, status.getNumDocksAvailable());
						insertStatus.setBoolean(7, status.isRenting());
						insertStatus.setBoolean(8, status.isInstalled());
						insertStatus.setBoolean(9, status.isReturning());
							
						insertStatus.executeUpdate();
					
					}else {
						System.out.println("Ya existen datos");
					}
				}
			}
		}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/** Funcion que sirve para comprobar si los datos ya han sido insertados en un status estacion*/
	public boolean statusExists(Connection connection, int stationId, Timestamp date) {
		
		try (PreparedStatement check = connection.prepareStatement( // Creamos la consulta de preparacion
				    "select id_estacion from registro_estacion where id_estacion = ? and fecha = ?"
			)){
			
			// Añadimos a la consulta las condiciones que estan en ?
			check.setInt(1, stationId);
			check.setTimestamp(2, date);
			
			try(ResultSet result = check.executeQuery()){ // ejecutamos la consulta
			
				return(result.next()); // devolvemos el resultado
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	
		return false; // devolvemos false si hay fallos
	}
	

}
