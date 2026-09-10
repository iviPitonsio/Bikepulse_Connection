package com.bikepulse.bikepulse_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikepulse.bikepulse_api.DataUpdater;
import com.bikepulse.bikepulse_api.repositories.StationDataRepository;
import com.bikepulse.bikepulse_api.repositories.StationRepository;
import com.bikepulse.bikepulse_api.repositories.StationStatusRepository;
import com.bikepulse.bikepulse_api.stationData.Station;
import com.bikepulse.bikepulse_api.stationData.StationStatus;
import com.bikepulse.bikepulse_api.stations.StationBikes;
import com.bikepulse.bikepulse_api.stations.StationCurrentStatus;
import com.bikepulse.bikepulse_api.stations.StationSummary;

import utils.BikeEnum;

@RestController
public class Controller {
	
	private StationRepository stationRepo;
	private StationStatusRepository statusRepo;
	private StationDataRepository dataRepo;
	private DataUpdater dataUpdater;
	
	// Constructor que recibe la informacion que hemos obtenido en la consulta SQL
	public Controller(StationRepository repoStation, StationStatusRepository repoStatus, StationDataRepository repoData, DataUpdater dataUpdater) {
		stationRepo = repoStation;
		statusRepo = repoStatus;
		dataRepo = repoData;
		this.dataUpdater = dataUpdater;
	}


	/** Funcion que muestra todos los datos de cada estacion*/
    @GetMapping("/stations") // nombre que define al metodo con el que enviaremos los datos de cada estacion => formara parte de la URL donde podremos verlos
    public List<Station> getStations() {
        
        return stationRepo.selectStations(); // enviamos los datos
        
    }
    
    /** Funcion que muestra una estacion en concreto*/
    @GetMapping("/station/{id}") 
    public Station getStation(@PathVariable int id) {
        
        return stationRepo.selectStation(id); 
        
    }
	
	/**Funcion que muestra el historial de una estacion en concreto*/
    @GetMapping("/station/{id}/history")
    public List<StationStatus> getStationStatus(@PathVariable int id) {
        
        return statusRepo.stationStatus(id);
        
    }
	
	/** Funcion que muestra el nombre, capacidad, id y bicis disponibles de las estaciones que estan llenas*/
	@GetMapping("/station/full")
    public List<StationBikes> getFullStations() {
        
        return dataRepo.selectBikeStations(BikeEnum.FULL);
        
    }
	
	
	/** Funcion que muestra el nombre, capacidad, id y bicis disponibles de las estaciones que estan vacias*/
	@GetMapping("/station/empty")
    public List<StationBikes> getEmptyStations() {
        
        return dataRepo.selectBikeStations(BikeEnum.EMPTY);
        
    }
	
	/** Funcion que muestra el nombre, bicis, huecos, fecha, alquiler, devolucion e id de la estacion que queramos en su estado mas reciente*/
	@GetMapping("/station/{id}/status")
	public StationCurrentStatus getCurrentStatus(@PathVariable int id){
		
		return dataRepo.selectCurrentStatus(id);
	}
	
	/** Funcion que muestra el estado actual de la red de estaciones*/
	@GetMapping("/station/summary")
	public StationSummary getStationSummary() {
		
		return dataRepo.selectStationSummary();
	}
	
	/** Funcion que muestra el estado actual de la red de estaciones*/
	@GetMapping("/station/ranking")
	public List<StationBikes> getStationRanking() {
		
		return dataRepo.selectBikeStations(BikeEnum.RANKING);
	}
	
	/** Solo cuando Spring este listo, se ejecutara la actualizacion de datos*/
	@PostMapping("/update")
	public void updateData() {
	    dataUpdater.updateData();
	}
	
}
