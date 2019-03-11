package com.example.inventory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.attoparser.config.ParseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Benjamin
 *
 */
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
	public Boolean login(@RequestBody String payload) throws SQLException, ClassNotFoundException {
		//The controller receives the request from the front end and then sends it
		//to inventoryManagement to perform processing. 
		//DashboardData dashData = new DashboardData();
		
		/*boolean authenticated = false;
		String username = payload.get("username");
		String password = payload.get("password");*/
		try {
			JsonNode jsonNode = new ObjectMapper().readTree(payload);
			String username = jsonNode.get("username").asText();
			String password = jsonNode.get("password").asText();
			
			Boolean authenticated = inventoryManagement.authenticateIntoApplication(username, password); 
			return authenticated;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	//test with: curl -H "Content-Type: application/json" --data '{"bucketName":"Unit1","partNumbersAllowed":"123789-121", "department":"testDept", "unitOfMeasurement":"pounds", "maxMeasurement":"300", "location":"testLocation"}' @body.json http://localhost:8080/createDigitalStorageItem
	@RequestMapping(value = "/createDigitalStorageItem")
	@ResponseBody
	public Boolean createDigitalStorageItem(@RequestBody String payload) throws SQLException {
		/*String bucketName = payload.get("bucketName");
		String partNumbersAllowed = payload.get("partNumbersAllowed");
		String department = payload.get("department");
		String unitOfMeasurement = payload.get("unitOfMeasurement");
		String maxMeasurement = payload.get("maxMeasurement");
		String location = payload.get("location");
		int maxMeasConverted = Integer.parseInt(maxMeasurement);*/
		
		try {
			JsonNode jsonNode = new ObjectMapper().readTree(payload);
			String bucketName = jsonNode.get("bucketName").asText();
			String partNumbersAllowed = jsonNode.get("partNumbersAllowed").asText();
			String department = jsonNode.get("department").asText();
			String unitOfMeasurement = jsonNode.get("unitOfMeasurement").asText();
			int maxMeasurement = jsonNode.get("maxMeasurement").asInt();
			String location = jsonNode.get("location").asText();
			
			boolean response = inventoryManagement.createDigitalStorageItem(bucketName, partNumbersAllowed, department, unitOfMeasurement, maxMeasurement, location);
			return response;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

/*	@RequestMapping(value = "/addPartsToStorage")
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
*/
	
	@RequestMapping(value = "/removePartsToStorage")
	@ResponseBody 
	public Boolean removePartsToStorage(@RequestBody String payload) throws SQLException {
		/*String bucketID = payload.get("bucketID");
		int bucketIDconverted = Integer.parseInt(bucketID);
		String partNumber = payload.get("partNumber");
		String serialNumber = payload.get("serialNumber");*/
		try {
			JsonNode jsonNode = new ObjectMapper().readTree(payload);
			int bucketID = jsonNode.get("bucketID").asInt();
			String partNumber = jsonNode.get("partNumber").asText();
			String serialNumber = jsonNode.get("serialNumber").asText();
			
			boolean response = inventoryManagement.removePartsToStorage(bucketID, partNumber, serialNumber);
			return response;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/partSetUp")
	@ResponseBody 
	public Boolean setUpPartNumber(@RequestBody String payload) throws SQLException {
		/*String partNumber = payload.get("partNumber");
		String trackByWeight = payload.get("trackByWeight");
		int trackByWeightConverted = Integer.parseInt(trackByWeight);
		String weight = payload.get("weight");
		double weightConverted = Integer.parseInt(weight);*/
		
		try {
			JsonNode jsonNode = new ObjectMapper().readTree(payload);
			String partNumber = jsonNode.get("partNumber").asText();
			int trackByWeight = jsonNode.get("trackByWeight").asInt();
			int weight = jsonNode.get("weight").asInt();
			
			boolean response = inventoryManagement.setUpPartNumber(partNumber, trackByWeight, weight);
			return response;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping(value = "/addPartsToStorage")
	@ResponseBody
	public Boolean addPartsToStorage(@RequestBody String payload) throws SQLException{

		try {
			JsonNode jsonNode = new ObjectMapper().readTree(payload);
			//PartsInStorage items = new PartsInStorage();
			String username = jsonNode.get("username").asText();
			String csrf = jsonNode.get("csrf").asText();
			String department = jsonNode.get("department").asText();
			int unit = jsonNode.get("unit").asInt();
			String type = jsonNode.get("type").asText();
			int hasWeight = jsonNode.get("hasWeight").asInt();
			int serialNo = jsonNode.get("serialNo").asInt();
			int partNo = jsonNode.get("partNo").asInt();
			int weight = jsonNode.get("weight").asInt();
			
			boolean response = inventoryManagement.addPartsToStorage(username, csrf, department, unit, type, hasWeight, serialNo, partNo, weight);
			return response;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*try {
		    ObjectMapper mapper = new ObjectMapper();
			JsonNode nameInfo = mapper.readValue(payload, JsonNode.class);
		    JsonNode aUser = nameInfo.get("username");
		    String username = aUser.asText();
		    
			JsonNode csrfInfo = mapper.readValue(payload, JsonNode.class);
		    JsonNode aCsrf = csrfInfo.get("csrf");
		    String csrf = aCsrf.asText();
		    
			JsonNode deptInfo = mapper.readValue(payload, JsonNode.class);
		    JsonNode adept = deptInfo.get("department");
		    String department = adept.asText();
		    
			JsonNode unitInfo = mapper.readValue(payload, JsonNode.class);
		    JsonNode aUnit = unitInfo.get("unit");
		    int unit = aUnit.asInt();
		    
			JsonNode typeInfo = mapper.readValue(payload, JsonNode.class);
		    JsonNode atype = typeInfo.get("type");
		    String type = atype.asText();
		    
			JsonNode hasWeightInfo = mapper.readValue(payload, JsonNode.class);
		    JsonNode aHasWeight = hasWeightInfo.get("hasWeight");
		    int hasWeight = aHasWeight.asInt();
		    
			JsonNode snInfo = mapper.readValue(payload, JsonNode.class);
		    JsonNode aSn = snInfo.get("serialNo");
		    int serialNo = aSn.asInt();
		    
			JsonNode pnInfo = mapper.readValue(payload, JsonNode.class);
		    JsonNode aPn = pnInfo.get("partNo");
		    int partNo = aPn.asInt();
		    
			JsonNode weightInfo = mapper.readValue(payload, JsonNode.class);
		    JsonNode aWeight = weightInfo.get("weight");
		    int weight = aWeight.asInt();
		    
			boolean response = inventoryManagement.addPartsToStorage(username, csrf, department, unit, type, hasWeight, serialNo, partNo, weight);
			return response;
		    
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return false;
	}

}
