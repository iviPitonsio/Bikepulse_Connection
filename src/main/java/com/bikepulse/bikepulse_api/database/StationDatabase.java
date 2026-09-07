package com.bikepulse.bikepulse_api.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bikepulse.bikepulse_api.stationData.Station;

@Repository
public class StationDatabase {
	
	private DatabaseConnection database;
	
	public StationDatabase(DatabaseConnection database) {
		this.database = database; // Nos conectamos a la base de datos
	}
	
	/** Funcion que inserta los datos en la tabla estacion*/
	public void StationConnection(List<Station> stations) {
		
		// Preparamos el envio de datos de la tabla de las estaciones
			
			try(Connection stationConnection = database.getConnection()){
			
				try(PreparedStatement insertStation = stationConnection.prepareStatement
					("insert into estacion (id_estacion, id_externo, nombre, direccion, cp, capacidad, latitud, longitud, es_carga, es_virtual) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")
					){

			
			//Insertamos los datos en el orden en el que estan los datos en la tabla(indice del elemento de la consulta, dato)
			
			for(Station i: stations) {
				
				// Comprobamos si los datos estan insertados, si no lo estan, los insertamos
				if(!stationExists(stationConnection, i.getStationId())) {
			
					insertStation.setInt(1, i.getStationId());
					insertStation.setString(2, i.getExternalId());
					insertStation.setString(3, i.getNames().get(0).getText());
					insertStation.setString(4, i.getAddress());
					insertStation.setString(5, i.getPostCode());
					insertStation.setInt(6, i.getCapacity());
					insertStation.setDouble(7, i.getLat());
					insertStation.setDouble(8, i.getLon());
					insertStation.setBoolean(9, i.isChargingStation());
					insertStation.setBoolean(10, i.isVirtualStation());
						
					insertStation.executeUpdate();
				
				
				}else {
					System.out.println("La estación ya existe");
				}
				
			}
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	/** Funcion que sirve para comprobar si los datos ya han sido insertados en una estacion*/
	public boolean stationExists(Connection connection, int stationId) {
		
		try (PreparedStatement check = connection.prepareStatement( // Creamos la consulta de preparacion
				    "select id_estacion from estacion where id_estacion = ?")){
			
			check.setInt(1, stationId); // añadimos a la consulta la condicion que esta en ?
			
			try(ResultSet result = check.executeQuery()){ // realizamos la consulta
			
				return(result.next()); // devolvemos el resultado
			
			}
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	
		return false; // devolvemos false si hay algun error
	}

}
