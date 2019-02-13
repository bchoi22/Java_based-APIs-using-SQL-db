package com.example.inventory;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

	public String authenticateIntoApplication(Map<String, Object> payload) {
		// TODO Auto-generated method stub
		//Perform logic to authenticate into the application
		return "You're In!!";
	}



}

