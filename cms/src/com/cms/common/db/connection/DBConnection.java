package com.cms.common.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection con;
	private DBConnection() {}
	
	public static Connection getConnection() {
		return (createConnection() );
	}
	private static Connection  createConnection()  {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
//			13.127.156.166
//			LIVE CONNECTION
//			con=DriverManager.getConnection( "jdbc:mysql://13.127.156.166:3306/projectsdb_new","root","P@ss@2018");
//		LOCAL CONNECTION	
			con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/projectsdb_new","root","password");
			System.out.println("Connection created..!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void main(String[] args) throws SQLException {
		getConnection();
	}
}
