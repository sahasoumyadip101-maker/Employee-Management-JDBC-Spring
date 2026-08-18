package com.aot.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
	
	private static final String USERNAME="DB_USERNAME";
	
	private static final String PASSWORD="DB_PASSWORD";
	
	private ConnectionFactory() {
	}
	
	public static Connection getConnection() throws SQLException{
		//connecton establishment code --
		return DriverManager.getConnection(URL,USERNAME,PASSWORD);
	}
}