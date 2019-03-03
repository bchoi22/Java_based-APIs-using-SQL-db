package com.example.inventory;

import java.sql.SQLException;
import java.util.Map;

import org.attoparser.config.ParseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

	@Autowired
	InventoryManagementApplication inventoryManagement;


	/*login method is set up to take a json 
	 * payload and return a string as a response. 
	 * This is just for testing. Eventually it will return
	 * a json object containing all of the data needed to 
	 * build the users dashboard if they successfully authenticate in.
	 * 
	 * test with: curl -H "Content-Type: application/json" --data '{"username":"xyz","password":"123"}' @body.json http://localhost:8080/login
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Boolean login(@RequestBody Map<String, String> payload) throws SQLException, ClassNotFoundException {
		//The controller receives the request from the front end and then sends it
		//to inventoryManagement to perform processing. 
		//DashboardData dashData = new DashboardData();
		boolean authenticated = false;
		String username = payload.get("username");
		String password = payload.get("password");

		authenticated = inventoryManagement.authenticateIntoApplication(username, password); 

		return authenticated;
	}

	//test with: curl -H "Content-Type: application/json" --data '{"bucketName":"Unit1","partNumbersAllowed":"123789-121", "department":"testDept", "unitOfMeasurement":"pounds", "maxMeasurement":"300", "location":"testLocation"}' @body.json http://localhost:8080/createDigitalStorageItem
	@RequestMapping(value = "/createDigitalStorageItem")
	@ResponseBody
	public Boolean createDigitalStorageItem(@RequestBody Map<String, String> payload) throws SQLException {
		String bucketName = payload.get("bucketName");
		String partNumbersAllowed = payload.get("partNumbersAllowed");
		String department = payload.get("department");
		String unitOfMeasurement = payload.get("unitOfMeasurement");
		String maxMeasurement = payload.get("maxMeasurement");
		String location = payload.get("location");
		int maxMeasConverted = Integer.parseInt(maxMeasurement);
		boolean response = inventoryManagement.createDigitalStorageItem(bucketName, partNumbersAllowed, department, unitOfMeasurement, maxMeasConverted, location);

		return response;
	}

	@RequestMapping(value = "/addPartsToStorage")
	@ResponseBody 
	public Boolean addPartsToStorage(@RequestBody Map<String, String> payload) throws SQLException {
		//String itemID = payload.get("itemID");
		String username = payload.get("username");
		String csrf = payload.get("csrf");
		String department = payload.get("department");
		int unit = Integer.parseInt(payload.get("unit"));
		String type = payload.get("type");
		//boolean hasWeight = Boolean.parseBoolean(payload.get("hasWeight"));
		int hasWeight = Integer.parseInt(payload.get("hasWeight"));
		int serialNo = Integer.parseInt(payload.get("serialNo"));
		int partNo = Integer.parseInt(payload.get("partNo"));
		int weight = Integer.parseInt(payload.get("weight"));

		boolean response = inventoryManagement.addPartsToStorage(username, csrf, department, unit, type, hasWeight, serialNo, partNo, weight);
		return response;
	}

	
	@RequestMapping(value = "/removePartsToStorage")
	@ResponseBody 
	public Boolean removePartsToStorage(@RequestBody Map<String, String> payload) throws SQLException {
		String bucketID = payload.get("bucketID");
		int bucketIDconverted = Integer.parseInt(bucketID);
		String partNumber = payload.get("partNumber");
		String serialNumber = payload.get("serialNumber");

		boolean response = inventoryManagement.removePartsToStorage(bucketIDconverted, partNumber, serialNumber);
		return response;
	}
	
	@RequestMapping(value = "/partSetUp")
	@ResponseBody 
	public Boolean setUpPartNumber(@RequestBody Map<String, String> payload) throws SQLException {
		String partNumber = payload.get("partNumber");
		String trackByWeight = payload.get("trackByWeight");
		int trackByWeightConverted = Integer.parseInt(trackByWeight);
		String weight = payload.get("weight");
		double weightConverted = Integer.parseInt(weight);

		boolean response = inventoryManagement.setUpPartNumber(partNumber, trackByWeightConverted, weightConverted);
		return response;
	}
	

}
