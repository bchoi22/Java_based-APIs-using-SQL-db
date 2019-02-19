package com.example.inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

	public Boolean authenticateIntoApplication(String username, String password) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		//Perform logic to authenticate into the application
		boolean authenticated = false;
		//DashboardData dashBoardData = new DashboardData();
		Connection con = null;
		String connectionUrl = "jdbc:sqlserver://pyro-db.cc5cts2xsvng.us-east-2.rds.amazonaws.com:1433;databaseName=?;user=?;password=?";

		con = DriverManager.getConnection(connectionUrl);
		String sql = "SELECT * FROM dbo.Login where UserName = ? and password = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);

		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			while(rs.next()) {
				//Test
				authenticated = true;
			//	System.out.println("UserName: " + rs.getString("UserName") + " Password: " + rs.getString("Password") + " Admin: " + rs.getString("Admin"));
			}
			
		} 

		if(!con.isClosed()) {
			con.close();
		}
		
		/*
        //test response object
		dashBoardData.setName("Test Unit 1");
		dashBoardData.setLocation("Test Location");
		dashBoardData.setNumberOfItems(12);
		dashBoardData.setCapacity(23.0);
		dashBoardData.setCurrentWeight(256.0);
		
		return dashBoardData;
		*/
		return authenticated;
	}

	public String createDigitalStorageItem(Map<String, String> payload) {
		//Get next primary key to use for ID.
		int bucketKey = 0;
		Connection con = null;
		String connectionUrl = "jdbc:sqlserver://pyro-db.cc5cts2xsvng.us-east-2.rds.amazonaws.com:1433;databaseName=?;user=?;password=?";
		try {
			con = DriverManager.getConnection(connectionUrl);
			String sql = "SELECT max(BucketID) as bucketId FROM dbo.Buckets";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				bucketKey = rs.getInt("BucketId");
			}
			bucketKey++;
			
			//insert new Storage item into the buckets table.
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}



}

