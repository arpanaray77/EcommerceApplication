package com.ecommerce.userservice.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;



@Controller
public class MainController {

	String homepg= "http://localhost:8086/shop";
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> response = restTemplate.getForEntity(homepg, String.class);
	String url ="http://localhost:8008/create";
	//Product[] cart = restTemplate.getForObject(url, Product[].class);
	
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
	 
	 
	 @GetMapping("/addToCart/{id}")
		public ResponseEntity<String> addToCart(@PathVariable Long id) {
		 ResponseEntity<String> responseView = restTemplate.getForEntity("http://localhost:8008/addToCart/"+id, String.class);
	    	return responseView;
		}
	 String cartpg="http://localhost:8008/cartService";
	 @GetMapping("/cartService")
		public ResponseEntity<String> getCart(Model model)
		{
			ResponseEntity<String> responseView = restTemplate.getForEntity(cartpg, String.class);
	    	return responseView;
		}
		
		@GetMapping("/cartService/removeItem/{index}")
		public ResponseEntity<String> removeCartItem(@PathVariable int index)
		{
			ResponseEntity<String> responseView = restTemplate.getForEntity(cartpg+"/removeItem/"+index, String.class);
	    	return responseView;
		}
		
		@GetMapping("/checkout")
		public ResponseEntity<String> goCheckout(Model model)
		{
			ResponseEntity<String> responseView = restTemplate.getForEntity("http://localhost:8008/checkout/", String.class);
	    	return responseView;
		}
	 
	 }