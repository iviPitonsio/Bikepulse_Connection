package com.bikepulse.bikepulse_api.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.bikepulse.bikepulse_api.stations.StationBikes;
import com.bikepulse.bikepulse_api.stations.StationCurrentStatus;
import com.bikepulse.bikepulse_api.stations.StationSummary;

import utils.BikeEnum;

/** Clase que obtiene los datos de para las consultas que mezclan datos de 2 tablas*/
@Repository
public class StationDataRepository {
	
private DataSource database;
	
	// Constructor para establecer la conexión con MySQL (lo crea Spring automaticamente a partir de la informacion de aplication.properties
	public StationDataRepository(DataSource datasource) {
		
		database = datasource;
		
	}
	
	/** Funcion que devuelve los datos de las estaciones que esten llenas, vacias o un ranking del estado actual de estas*/
	public List<StationBikes> selectBikeStations(BikeEnum option){
		
		
		List<StationBikes> stations = new ArrayList<StationBikes>(); // creamos la lista con todos los datos de las estaciones
		String query;

		// Preparamos el envio de datos la tabla de las estaciones
		
		// escribimos la condicion de ambas consultas
		query = queryBikes(option);
			
	
		try(Connection stationConnection = database.getConnection();
				
		PreparedStatement getStation = stationConnection.prepareStatement 
			(query)){ 
			
			ResultSet result = getStation.executeQuery(); // hacemos la consulta a SQL

			 while (result.next()) { // Para cada fila de result, guardamos cada dato en un objeto Station y lo añadimos a la lista
				 
				 stations.add(bikesData(result));
				 
			
			 }
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return stations;
		
	}
	
	/** Funcion que guarda los datos del ultimo registro de una estacion que determinemos*/
	public StationCurrentStatus selectCurrentStatus(int stationId){

		try(Connection stationConnection = database.getConnection();
				
			PreparedStatement getStation = stationConnection.prepareStatement 
				("select nombre, bicis_disponibles, huecos_disponibles, capacidad, esta_alquilando, esta_devolviendo, fecha, e.id_estacion from estacion e join registro_estacion r on(r.id_estacion = e.id_estacion) "
						+ "join(select id_estacion, max(fecha) as \"ultima_fecha\" from registro_estacion where id_estacion = ?) as ultimos on \r\n"
						+ "(r.fecha = ultimos.ultima_fecha and r.id_estacion = ultimos.id_estacion)")){ 
					
			getStation.setInt(1, stationId); // asignamos el ID de la estacion de la que queramos hacer la consulta
			ResultSet result = getStation.executeQuery(); // hacemos la consulta a SQL

			 if (result.next()) { // Si el ID existe, guardamos los datos y devolvemos el objeto
						 
				 return statusData(result);
						 
					
			 }
					
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
				
		return null;
	}
	
	/** Funcion que devuelve un resumen actual del estado de las estaciones*/
	public StationSummary selectStationSummary() {
		
		try(Connection stationConnection = database.getConnection();
				
				PreparedStatement getStation = stationConnection.prepareStatement 
					("select count(e.id_estacion) as \"estaciones_totales\", count(case when esta_instalada = true then e.id_estacion end) as \"estaciones_activas\" , sum(bicis_disponibles) as \"bicis_totales\", sum(huecos_disponibles) as \"huecos_totales\", sum(capacidad) as \"capacidad_total\" "
							+ "from estacion e join registro_estacion r on(r.id_estacion = e.id_estacion) join (select id_estacion, max(fecha) as \"ultima_fecha\" from registro_estacion group by id_estacion) as ultimos on\r\n"
							+ "(r.fecha = ultimos.ultima_fecha and r.id_estacion = ultimos.id_estacion);")){ 

				ResultSet result = getStation.executeQuery(); // hacemos la consulta a SQL
				
				result.next(); // avanzamos a la primera y unica fila para devolverla
				
				return statusSummary(result);
							 	
						
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
					
			return null;
	}
	
	
	/** Metodo con el que guardamos los datos de cada objeto StationBikes*/
	public StationBikes bikesData(ResultSet result) throws SQLException {
		
		StationBikes stationBikes = new StationBikes(); // creamos el objeto que guardara los datos

		stationBikes.setName(result.getString("nombre")); // Sacamos el nombre
		stationBikes.setStationId(result.getInt("id_estacion")); // el id
		stationBikes.setCapacity(result.getInt("capacidad")); // la capacidad 
		stationBikes.setVehiclesAvailable(result.getInt("bicis_disponibles")); // las bicis disponibles
		
		return stationBikes;
		
	}
	
	
	/** Metodo con el que guardamos los datos de un objeto StationCurrentStatus*/
	public StationCurrentStatus statusData(ResultSet result) throws SQLException{
		
		StationCurrentStatus stationCurrent = new StationCurrentStatus(); // creamos el objeto que guardara los datos

		stationCurrent.setName(result.getString("nombre")); // Sacamos el nombre
		stationCurrent.setVehiclesAvailable(result.getInt("bicis_disponibles")); // sacamos las bicis disponibles
		stationCurrent.setStationId(result.getInt("id_estacion")); // el id
		stationCurrent.setRenting(result.getBoolean("esta_alquilando")); // alquiler
		stationCurrent.setReturning(result.getBoolean("esta_devolviendo")); // devolucion 
		stationCurrent.setDate(result.getTimestamp("fecha")); // fecha
		stationCurrent.setDocksAvailable(result.getInt("huecos_disponibles")); // huecos disponibles
		stationCurrent.setCapacity(result.getInt("capacidad")); // huecos disponibles
		
		return stationCurrent;
		
	}
	
	/** Metodo con el que guardamos los datos de un objeto StationSummary*/
	public StationSummary statusSummary(ResultSet result) throws SQLException{
		
		StationSummary stationSummary = new StationSummary(); // creamos el objeto que guardara los datos

		stationSummary.setTotalStations(result.getInt("estaciones_totales")); // guardamos las estaciones totales
		stationSummary.setActiveStations(result.getInt("estaciones_activas")); // estaciones activas
		stationSummary.setBikesAvailable(result.getInt("bicis_totales")); // bicis totales
		stationSummary.setDocksAvailable(result.getInt("huecos_totales")); // huecos totales
		stationSummary.setTotalCapacity(result.getInt("capacidad_total")); // capacidad total
		
		return stationSummary;
		
	}
	
	/** Funcion que devuelve la consulta a realizar dependiendo del enum que le llegue al metodo*/
	public String queryBikes(BikeEnum option) {
		
		if(option == BikeEnum.FULL) {
			return "select nombre, e.id_estacion, capacidad, bicis_disponibles "
					+ "from estacion e join registro_estacion r on(r.id_estacion = e.id_estacion) "
					+ "join(select id_estacion, max(fecha) as \"ultima_fecha\" from registro_estacion group by id_estacion) as ultimos on \r\n"
					+ "(r.fecha = ultimos.ultima_fecha and r.id_estacion = ultimos.id_estacion) where r.bicis_disponibles = e.capacidad and capacidad > 0 group by nombre, e.id_estacion, capacidad, bicis_disponibles;";
		
		}else if(option == BikeEnum.EMPTY){
			return "select nombre, e.id_estacion, capacidad, bicis_disponibles "
					+ "from estacion e join registro_estacion r on(r.id_estacion = e.id_estacion) "
					+ "join (select id_estacion, max(fecha) as \"ultima_fecha\" from registro_estacion group by id_estacion) as ultimos on \r\n"
					+ "(r.fecha = ultimos.ultima_fecha and r.id_estacion = ultimos.id_estacion) where r.bicis_disponibles = 0 and capacidad != 0 group by nombre, e.id_estacion, capacidad, bicis_disponibles;";
		
		}else
			return "select nombre, e.id_estacion, capacidad, bicis_disponibles "
			+ "from estacion e join registro_estacion r on (r.id_estacion = e.id_estacion) join(select id_estacion, max(fecha) as \"ultima_fecha\" from registro_estacion group by id_estacion) as ultimos on\r\n"
			+ "(r.id_estacion = ultimos.id_estacion and r.fecha = ultimos.ultima_fecha) group by nombre, e.id_estacion, capacidad, bicis_disponibles order by bicis_disponibles desc limit 5; ";
		
	}

}

