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

	public Boolean authenticateIntoApplication(String username, String password) throws SQLException  {
		boolean authenticated = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String connectionUrl = "jdbc:sqlserver://pyro-db.cc5cts2xsvng.us-east-2.rds.amazonaws.com:1433;databaseName=?;user=?;password=?";

		try {
			con = DriverManager.getConnection(connectionUrl);

			String sql = "SELECT * FROM dbo.Login where UserName = ? and password = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					//Test
					authenticated = true;
					//	System.out.println("UserName: " + rs.getString("UserName") + " Password: " + rs.getString("Password") + " Admin: " + rs.getString("Admin"));
				}

			} 
		} catch (SQLException e) {

			e.printStackTrace();
			authenticated = false;
		} finally {
			if(!con.isClosed()) {
				con.close();
			}

			if(!rs.isClosed()) {
				rs.close();
			}

			if(!ps.isClosed()) {
				ps.close();
			}

		}



		return authenticated;
	}

	public Boolean createDigitalStorageItem(String bucketName, String partNumbersAllowed, String department,
			String unitOfMeasurement, int maxMeasConverted, String location) throws SQLException {
		//Get next primary key to use for ID.

		int bucketKey = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//String connectionUrl = "jdbc:sqlserver://pyro-db.cc5cts2xsvng.us-east-2.rds.amazonaws.com:1433;databaseName=?;user=?;password=?";
		String connectionUrl = "jdbc:sqlserver://pyro-db.cc5cts2xsvng.us-east-2.rds.amazonaws.com:1433;databaseName=FuzzyDB;user=Fuzzies;password=abcdefg1234567";
		try {
			con = DriverManager.getConnection(connectionUrl);
			String sql = "SELECT max(BucketID) as bucketId FROM dbo.Buckets";
			Statement state = con.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next()) {
				bucketKey = rs.getInt("BucketId");
			}
			bucketKey++;

			//insert new Storage item into the buckets table.
			String insertSql = "insert into dbo.Buckets(BucketID, BucketName, PartNumbersAllowed, DepartmentID, UnitOfMeasurement, MaxMeasurement, Location) " +
					"VALUES(?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(insertSql);
			ps.setInt(1, bucketKey);
			ps.setString(2, bucketName);
			ps.setString(3, partNumbersAllowed);
			ps.setString(4, department);
			ps.setString(5, unitOfMeasurement);
			ps.setInt(6, maxMeasConverted);
			ps.setString(7, location);

			ps.executeUpdate();
		

		} 
		catch (SQLException e) {

			e.printStackTrace();
			return false;
		} 
		finally {
			if(!con.isClosed()) {
				con.close();
			}

			if(!rs.isClosed()) {
				rs.close();
			}

			if(!ps.isClosed()) {
				ps.close();
			}
		}

		return true;
	}





}

