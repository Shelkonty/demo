package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class ProjectAuctionBackendApplication {
	public static ProjectAuctionBackendApplication.DBConnection DBConnection;

	public class DBConnection{

		private static Connection conn;

		static {
			try
			{
				Class.forName("org.postgresql.Driver", true, ClassLoader.getSystemClassLoader());
				java.sql.Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project-auction?useSSL=false", "postgres", "1234");
			}
			catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
		public static Connection getCon(){
			return conn;
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(ProjectAuctionBackendApplication.class, args);
	}

}
