package com.bikepulse.bikepulse_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication // iniciamos Spring Boot
@EnableScheduling // habilitamos el sistema de programacion de tareas de SB
public class BikepulseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikepulseApiApplication.class, args);
	}

}
