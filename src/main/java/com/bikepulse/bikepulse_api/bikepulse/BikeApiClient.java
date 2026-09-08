package com.bikepulse.bikepulse_api.bikepulse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.bikepulse.bikepulse_api.stationData.Station;
import com.bikepulse.bikepulse_api.stationData.StationResponse;
import com.bikepulse.bikepulse_api.stationData.StationStatus;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

	/** Funcion con la que gestionamos el recibo de datos de la API de BiciCoruña*/
	public class BikeApiClient {	

		
		/** Funcion que devuelve todos los datos de cada estacion*/
		public static List<Station> getStations() throws Exception{
			
			// Llamamos a la funcion getData() para obtener los datos
			StationResponse<Station> station = getData("https://acoruna.publicbikesystem.net/customer/gbfs/v3.0/station_information", new TypeReference<StationResponse<Station>>() {});
			List<Station> stations = station.getData().getStations(); // creamos una lista con todas las estaciones
			
			return stations;
		}
		
		
		/** Funcion que devuelve todos los datos de los registros de cada estacion*/
		public static List<StationStatus> getStationStatus() throws Exception{
			
			// Llamamos a la funcion getData() para obtener los datos
			StationResponse<StationStatus> status = getData("https://acoruna.publicbikesystem.net/customer/gbfs/v3.0/station_status", new TypeReference<StationResponse<StationStatus>>() {});
			List<StationStatus> statuses = status.getData().getStations(); // creamos una lista con todos los estados de cada estacion
			
			return statuses;
		}
		
		
		/** Funcion que recibe los datos de la API*/
		private static <T> T getData(String url, TypeReference<T> type) throws Exception {
			
			// Creamos el cliente
			HttpClient client = HttpClient.newHttpClient();
			
			
			// Creamos el ObjectMapper
			ObjectMapper objectMapper = new ObjectMapper();
			
			
			// Construimos la solicitud para todas las APIs
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create(url))
					    .timeout(java.time.Duration.ofSeconds(30)) //tiempo que damos para obtener los datos
					    .GET()
					    .build();

					
					HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Enviamos y recibimos respuesta
					String json = response.body(); // Asignamos las respuestas a un arrayList de Strings
					
					T result = objectMapper.readValue(json, type); // Convertimos el json en informacion legible para Java	
					
					return result;

				
		}
	
	}