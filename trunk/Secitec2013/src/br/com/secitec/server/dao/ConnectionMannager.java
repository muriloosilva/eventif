package br.com.secitec.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMannager {
	
	static boolean openShift = true;
	
	public static Connection getConnetion(){
		
		Connection conn = null;
		
		if(!openShift){
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Semanact", "postgres", "123");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else{
	    	String hostBD = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
	    	String portBD = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");
	    	String nameBD = System.getenv("OPENSHIFT_APP_NAME");
	    	String userBD = System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME");
	    	String passwdBD = System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD");
	    	
	    	try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection("jdbc:postgresql://"+hostBD+":"+portBD+"/"+nameBD, userBD, passwdBD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}


}