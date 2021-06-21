package com.ecommerce.userservice.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

	String homepg= "http://localhost:8086/shop";
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> response = restTemplate.getForEntity(homepg, String.class);
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping({"/","/shop"})
	public ResponseEntity<String> home() {
		return response;
	} 	
	
	 @GetMapping("/shop/category/{categoryId}")
	    public ResponseEntity<String> shopByCategory(@PathVariable Integer categoryId)
	    {
	    	ResponseEntity<String> responseSearch = restTemplate.getForEntity(homepg +"/category/"+categoryId, String.class);
	    	return responseSearch;
	    } 
	 
	 @GetMapping("/shop/viewproduct/{id}")
	    public ResponseEntity<String> viewProduct(@PathVariable Long id)
	    {
		    ResponseEntity<String> responseView = restTemplate.getForEntity(homepg +"/viewproduct/"+id, String.class);
	    	return responseView;
	    }  
}
