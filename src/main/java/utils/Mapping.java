package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bikepulse.bikepulse_api.stationData.StationStatus;

public class Mapping {
	
	/** Funcion para crear el Map que nos permita unir ambas clases de estaciones*/
	public static Map<Integer, StationStatus> crearMap(List<StationStatus> statuses) {
		
		// Creamos un Map, para relacionar los IDs de ambas clases
		Map<Integer, StationStatus> statusMap = new HashMap<>();
	
		
		for(StationStatus i: statuses) {
			statusMap.put(i.getStationId(), i); /*  Relacionamos el ID de cada estacion con el objeto StationStatus, 
													de forma que a partir del ID, obtendremos toda la info de su estacion*/
		}
		
		return statusMap;
	}

}
