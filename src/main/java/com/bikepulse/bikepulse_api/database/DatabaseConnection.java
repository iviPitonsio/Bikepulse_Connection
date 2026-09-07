package com.bikepulse.bikepulse_api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** Clase que sirve para conectarse a nuestra base de datos usando los datos de application.properties*/
@Component
public class DatabaseConnection { // Conectamos los datos de application.properties

	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String user;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	
	/** Nos conectamos a MySQL con las credenciales*/
	public Connection getConnection() throws SQLException {
		
		Connection conn = DriverManager.getConnection(url, user, password);
		    
		if (conn != null) {
			System.out.println("Connected to the database");
		}
		    
		return conn;
	}
	
}
