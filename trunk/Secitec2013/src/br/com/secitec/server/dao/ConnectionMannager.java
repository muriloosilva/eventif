package br.com.secitec.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMannager {
	
	
	public static Connection getConnetion(){
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TesteEventif", "postgres", "1234");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		
		return conn;
	}


}