package com.ecommerce.userservice.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

	RestTemplate restTemplate = new RestTemplate();
	String indexpg = "http://localhost:8086/shop";
	ResponseEntity<String> response = restTemplate.getForEntity(indexpg, String.class);
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public ResponseEntity<String> home() {
		return response;
	}
    
	
	
}
