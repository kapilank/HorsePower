package com.webService;

import java.sql.Connection;
//package models;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
		public static Connection getConnection()
		{
			Connection connect=null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://www.papademas.net/fp?user=dbfp&password=510");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			return connect;
		}
		
}
