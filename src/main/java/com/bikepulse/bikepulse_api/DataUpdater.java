package com.bikepulse.bikepulse_api;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bikepulse.bikepulse_api.database.StationDatabase;
import com.bikepulse.bikepulse_api.database.StationStatusDatabase;
import com.bikepulse.bikepulse_api.stationData.Station;
import com.bikepulse.bikepulse_api.stationData.StationStatus;

import com.bikepulse.bikepulse_api.bikepulse.BikeApiClient;
import jakarta.annotation.PostConstruct;
import utils.Mapping;

/** Clase que se encargara de actualizar cada cierto tiempo los datos de las estaciones*/
@Service
public class DataUpdater {
	
	private StationDatabase stationDatabase;
	private StationStatusDatabase stationStatusDatabase;
	
	/**Establecemos la conexion con la base de datos*/
	public DataUpdater(StationDatabase stationDatabase, StationStatusDatabase stationStatusDatabase) {
		this.stationDatabase = stationDatabase;
		this.stationStatusDatabase = stationStatusDatabase;
	}

	/** Funcion con la que actualizamos los datos de la BD*/
	@Scheduled(fixedRate = 5, initialDelay = 5, timeUnit = TimeUnit.MINUTES) // programamos la actualizacion de datos
	public void updateData() {
		
		try {

		// Creamos la lista de estaciones y la lista de su estado actual
			List<Station> stations = BikeApiClient.getStations();
			List<StationStatus> statuses = BikeApiClient.getStationStatus();
							
				
		// Creamos el Map, para poder enviar correctamente los daos de stationStatus mas tarde
			Map<Integer, StationStatus> statusMap = Mapping.crearMap(statuses);


		// Enviamos los datos a la tabla Station
			stationDatabase.StationConnection(stations);
						
						
		// Preparamos el envío de datos a tabla registro_Estacion
			stationStatusDatabase.StatusConnection(stations, statusMap);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct /** Solo cuando Spring este listo, se ejecutara la actualizacion de datos*/
	public void executeUpdate() {
		updateData();
	}
}
