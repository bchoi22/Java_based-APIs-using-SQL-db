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


	public boolean addPartsToStorage(int bucketIDconverted, String partNumber, String serialNumber) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psInsert = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		String connectionUrl = "jdbc:sqlserver://pyro-db.cc5cts2xsvng.us-east-2.rds.amazonaws.com:1433;databaseName=FuzzyDB;user=Fuzzies;password=abcdefg1234567";

		try {
			con = DriverManager.getConnection(connectionUrl);

			String sql = "SELECT * FROM dbo.Items where BucketID = ? and SerialNumber = ? and PartNumber = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bucketIDconverted);
			ps.setString(2, serialNumber);
			ps.setString(3,  partNumber);


			rs = ps.executeQuery();
			if(rs.isBeforeFirst()) {
				//while(rs.next()) {
					return false;
					//	System.out.println("UserName: " + rs.getString("UserName") + " Password: " + rs.getString("Password") + " Admin: " + rs.getString("Admin"));
				//}

			} else {
				int itemID = 0;
				
				String sqlMaxID = "SELECT max(ItemID) as itemId FROM dbo.Items";
				Statement state = con.createStatement();
				rs2 = state.executeQuery(sqlMaxID);
				if(rs2.isBeforeFirst()) {
					while(rs2.next()) {
						itemID = rs2.getInt("itemId");
					}
				}
				itemID++;
				String sqlInsert = "INSERT INTO dbo.Items(ItemID, BucketID, PartNumber, SerialNumber) " + 
						"values(?, ?, ?, ?)";
				psInsert = con.prepareStatement(sqlInsert);
				psInsert.setInt(1, itemID);
				psInsert.setInt(2, bucketIDconverted);
				psInsert.setString(3, partNumber);
				psInsert.setString(4, serialNumber);

				psInsert.executeUpdate();

			}
		} catch (SQLException e) {

			e.printStackTrace();
			//partLoaded = false;
			return false;
		} finally {
			if(!con.isClosed() && con != null) {
				con.close();
			}

			if(!rs.isClosed() && rs != null) {
				rs.close();
			}

			if(!ps.isClosed() && ps != null) {
				ps.close();
			}
			/*
			if(!psInsert.isClosed() && psInsert != null) {
				psInsert.close();
			}
			if(!rs2.isClosed() && rs2 != null) {
				rs2.close();
			}
		*/
		}

		return true;
	}

	public boolean removePartsToStorage(int bucketIDconverted, String partNumber, String serialNumber) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//Boolean partLoaded = false;
		PreparedStatement ps2 = null;
		String connectionUrl = "jdbc:sqlserver://pyro-db.cc5cts2xsvng.us-east-2.rds.amazonaws.com:1433;databaseName=FuzzyDB;user=Fuzzies;password=abcdefg1234567";

		try {
			con = DriverManager.getConnection(connectionUrl);

			String sql = "SELECT * FROM dbo.Items where BucketID = ? and SerialNumber = ? and PartNumber = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bucketIDconverted);
			ps.setString(2, serialNumber);
			ps.setString(3,  partNumber);
		

			rs = ps.executeQuery();
			if(rs.isBeforeFirst()) {
				String sqlDelete = "DELETE FROM dbo.Items where BucketID = ? and SerialNumber = ? and PartNumber = ?";
				
				ps2 = con.prepareStatement(sqlDelete);
				ps2.setInt(1, bucketIDconverted);
				ps2.setString(2,  serialNumber);
				ps2.setString(3,  partNumber);
				ps2.executeUpdate();
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			//partLoaded = false;
			return false;
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
			/*
			if(!ps2.isClosed()) {
				ps2.close();
			}
*/
		}
		return true;
	}

	public boolean setUpPartNumber(String partNumber, int trackByWeightConverted, double weightConverted) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psInsert = null;
		ResultSet rs = null;

		String connectionUrl = "jdbc:sqlserver://pyro-db.cc5cts2xsvng.us-east-2.rds.amazonaws.com:1433;databaseName=FuzzyDB;user=Fuzzies;password=abcdefg1234567";

		try {
			con = DriverManager.getConnection(connectionUrl);

			String sql = "SELECT * FROM dbo.PartNumbers where PartNumber = ? and TrackByWeight = ? and Weight = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, partNumber);
			ps.setInt(2, trackByWeightConverted);
			ps.setDouble(3,  weightConverted);

			rs = ps.executeQuery();
			if(rs.isBeforeFirst()) {
				
				return false;
					
			} else {
				
				String sqlInsert = "INSERT INTO dbo.PartNumbers(PartNumber, TrackByWeight, Weight) " + 
						"values(?, ?, ?)";
				psInsert = con.prepareStatement(sqlInsert);
				psInsert.setString(1, partNumber);
				if (trackByWeightConverted != 0)
					psInsert.setInt(2, 1);
				else
					psInsert.setInt(2, 0);
				psInsert.setDouble(3, weightConverted);

				psInsert.executeUpdate();

			}
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
			
		} finally {
			if(!con.isClosed() && con != null) {
				con.close();
			}

			if(!rs.isClosed() && rs != null) {
				rs.close();
			}

			if(!ps.isClosed() && ps != null) {
				ps.close();
			}
			/*
			if(!psInsert.isClosed() && psInsert != null) {
				psInsert.close();
			}
		*/
		}
		return true;
	}

}

