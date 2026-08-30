package com.bikepulse.bikepulse_api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import station.Station;
import station.StationStatus;

@RestController
public class Controller {
	
	private StationRepository stationRepo;
	private StationStatusRepository statusRepo;
	private StationBikesRepository bikeRepo;
	//private StationHistoryRepository historyRepo;
	
	// Constructor que recibe la informacion que hemos obtenido en la consulta SQL
	public Controller(StationRepository repoStation, StationStatusRepository repoStatus, StationBikesRepository repoBikes) {
		stationRepo = repoStation;
		statusRepo = repoStatus;
		bikeRepo = repoBikes;
	}


	/** Funcion que muestra todos los datos de cada estacion*/
    @GetMapping("/stations") // nombre que define al metodo con el que enviaremos los datos de cada estacion
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
        
        return bikeRepo.selectBikeStations(true);
        
    }
	
	
	/** Funcion que muestra el nombre, capacidad, id y bicis disponibles de las estaciones que estan vacias*/
	@GetMapping("/station/empty")
    public List<StationBikes> getEmptyStations() {
        
        return bikeRepo.selectBikeStations(false);
        
    }
	
	
}
