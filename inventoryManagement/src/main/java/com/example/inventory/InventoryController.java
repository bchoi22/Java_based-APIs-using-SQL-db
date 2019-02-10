package com.example.inventory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InventoryController {
	
	
	/*
	 * This is our login page 
	 * and main enterence into the app.
	 */
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	
	/*
	 * Once authenticated they will
	 * be forwarded to the dashboard.
	 */
	@RequestMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }
}
