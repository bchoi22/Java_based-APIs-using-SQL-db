package com.example.inventory;

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
	@RequestMapping(
			value = "/login",
			method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody Map<String, Object> payload) {
		//The controller receives the request from the front end and then sends it
		//to inventoryManagement to perform processing. 
		String response = inventoryManagement.authenticateIntoApplication(payload); 
		return response;
	}
	
	
	@RequestMapping("/createDashboard")
	@ResponseBody
    public String createDashboard() {
        return "dashboard";
    }
	
	
	
	@RequestMapping("/saveDashboard")
	@ResponseBody
    public String saveDashboard() {
        return "dashboard";
    }
}
