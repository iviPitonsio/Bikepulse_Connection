package com.bikepulse.bikepulse_api.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import station.LocalizedName;
import station.Station;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;

@Repository
public class StationRepository {
	
	private DataSource database;
	
	// Constructor para establecer la conexión con MySQL (lo crea Spring automaticamente a partir de la informacion de aplication.properties
	public StationRepository(DataSource datasource) {
		
		database = datasource;
		
	}
	
	/** Funcion que devuelve los datos de todas las estaciones*/
	public List<Station> selectStations(){
		
		
		List<Station> stations = new ArrayList<Station>(); // creamos la lista con todos los datos de las estaciones
	

	// Preparamos el envio de datos la tabla de las estaciones
	
		try(Connection stationConnection = database.getConnection();
		PreparedStatement getStation = stationConnection.prepareStatement 
			("select direccion, capacidad, id_externo, es_carga, es_virtual, latitud, longitud, cp, id_estacion, nombre from estacion;")){ // escribimos la consulta
			
			ResultSet result = getStation.executeQuery(); // hacemos la consulta a SQL
			
			 while (result.next()) { // Para cada fila de result, guardamos cada dato en un objeto Station y lo añadimos a la lista
				 
			     stations.add(createStation(result));
			 }
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return stations;
		
	}
	
	/** Funcion que devuelve los datos de una estacion en concreto*/
	public Station selectStation(int stationId){
		
		try(Connection stationConnection = database.getConnection();
				PreparedStatement getStation = stationConnection.prepareStatement 
					("select direccion, capacidad, id_externo, es_carga, es_virtual, latitud, longitud, cp, id_estacion, nombre from estacion where id_estacion = ?;")){ // escribimos la consulta
					
			getStation.setInt(1, stationId); // Asignamos al where de la consulta el id concreto
			
			ResultSet result = getStation.executeQuery(); // hacemos la consulta a SQL
			
			if(result.next()) { // Si el ID proporcionado existe, guardamos los datos
			    
			    return createStation(result);
			}
			
			 
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		 return null;
		 
	}
	
	/** Funcion que setea todos los datos de cada estacion*/
	public Station createStation(ResultSet result) throws SQLException {
		
		Station station = new Station();
		station.setAddress(result.getString("direccion"));
		station.setCapacity(result.getInt("capacidad"));
		station.setExternalId(result.getString("id_externo"));
		station.setIsChargingStation(result.getBoolean("es_carga"));
	    station.setIsVirtualStation(result.getBoolean("es_virtual"));
	    station.setLat(result.getDouble("latitud"));
	    station.setLon(result.getDouble("longitud"));
	    station.setPostCode(result.getString("cp"));
	    station.setStationId(result.getInt("id_estacion"));
     
	    List<LocalizedName> names = new ArrayList<LocalizedName>();
	    LocalizedName name = new LocalizedName();
	    name.setText(result.getString("nombre"));
	    names.add(0,name);
	    station.setNames(names);
	    
	    return station;
	}
	
}
