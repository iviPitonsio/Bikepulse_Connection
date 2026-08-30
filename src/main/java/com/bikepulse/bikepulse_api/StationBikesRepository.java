package com.bikepulse.bikepulse_api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

/** Clase que obtiene los datos de las estaciones que estan llenas o vacias*/
@Repository
public class StationBikesRepository {
	
private DataSource database;
	
	// Constructor para establecer la conexión con MySQL (lo crea Spring automaticamente a partir de la informacion de aplication.properties
	public StationBikesRepository(DataSource datasource) {
		
		database = datasource;
		
	}
	
	/** Funcion que devuelve los datos de las estaciones que esten llenas o vacias dependiendo de la booleana*/
	public List<StationBikes> selectBikeStations(boolean full){
		
		
		List<StationBikes> stations = new ArrayList<StationBikes>(); // creamos la lista con todos los datos de las estaciones
		String where;

	// Preparamos el envio de datos la tabla de las estaciones
		
		// escribimos la condicion de ambas consultas
		if(full) {
			where = "r.bicis_disponibles = e.capacidad and capacidad > 0";
		
		}else {
			where = "r.bicis_disponibles = 0 and capacidad > 0";
		}
			
	
		try(Connection stationConnection = database.getConnection();
				
		PreparedStatement getStation = stationConnection.prepareStatement 
			("select nombre, e.id_estacion, capacidad, bicis_disponibles from estacion e join registro_estacion r on(r.id_estacion = e.id_estacion) join(select id_estacion, max(fecha) as \"ultima_fecha\" from registro_estacion group by id_estacion) as ultimos on \r\n"
					+ "(r.fecha = ultimos.ultima_fecha and r.id_estacion = ultimos.id_estacion) where " + where + " group by nombre, e.id_estacion, capacidad, bicis_disponibles;\r\n"
					+ "")){ 
			
			ResultSet result = getStation.executeQuery(); // hacemos la consulta a SQL

			 while (result.next()) { // Para cada fila de result, guardamos cada dato en un objeto Station y lo añadimos a la lista
				 
				 stations.add(bikesData(result));
				 
			
			 }
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return stations;
		
	}
	
	
	
	public StationBikes bikesData(ResultSet result) throws SQLException {
		
		StationBikes stationBikes = new StationBikes(); // creamos el objeto que guardara los datos

		stationBikes.setName(result.getString("nombre")); // Sacamos el nombre
			 
		stationBikes.setStationId(result.getInt("id_estacion")); // el id
		stationBikes.setCapacity(result.getInt("capacidad")); // la capacidad
			 
			 
		stationBikes.setNumVehiclesAvailable(result.getInt("bicis_disponibles")); // sacamos las bicis disponibles
		
		return stationBikes;
		
	}

}

