package com.app.sysco.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnDB {
	
	private static String url="jdbc:mysql://localhost:3306/suivicours_db";
	private static String user= "root";
	private static String pwd="";
	private static String driver = "com.mysql.cj.jdbc.Driver";
	
	public static void loadDriver() {
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		
		loadDriver();
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, pwd);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
