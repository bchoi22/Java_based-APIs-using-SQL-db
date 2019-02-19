package com.example.inventory;

import java.sql.SQLException;
import java.util.Map;

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
	 * test with: curl -H "Content-Type: application/json" --data '{"username":"xyz","password":"xyz"}' @body.json http://localhost:8080/login
	 */
	@RequestMapping("/login")
	@ResponseBody
	public DashboardData login(@RequestBody Map<String, String> payload) throws SQLException, ClassNotFoundException {
		//The controller receives the request from the front end and then sends it
		//to inventoryManagement to perform processing. 
		DashboardData dashData = new DashboardData();
		dashData = inventoryManagement.authenticateIntoApplication(payload); 

		return dashData;
	}


	@RequestMapping(value = "/createDigitalStorageItem")
	@ResponseBody
	public String createDigitalStorageItem(@RequestBody Map<String, String> payload) {
		String response = inventoryManagement.createDigitalStorageItem(payload);
		return "dashboard";
	}



	@RequestMapping("/saveDashboard")
	@ResponseBody
	public String saveDashboard() {
		return "dashboard";
	}
}
