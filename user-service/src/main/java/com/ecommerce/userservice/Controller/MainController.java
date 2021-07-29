package com.ecommerce.userservice.Controller;


import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.userservice.Model.User;
import com.ecommerce.userservice.Repository.UserRepository;

@Controller
public class MainController {
	
	@Autowired
	private UserRepository userRepo;
	
	String homepg= "http://localhost:8086/shop";
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> response = restTemplate.getForEntity(homepg, String.class);
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/getId")
    public String viewUserAccountForm(
            @AuthenticationPrincipal UserDetails userDetails,
            Principal principal,Authentication authentication,
            Model model) {
	 if(userDetails!=null)
	 {
        String userEmail = userDetails.getUsername();
        User user = userRepo.findUserByEmail(userEmail);
        model.addAttribute("userId", user.getId());
	 }
	 
	 else if (principal != null) {
	        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		    String email=token.getPrincipal().getAttributes().get("email").toString();
		    User user = userRepo.findUserByEmail(email);
	        model.addAttribute("userId", user.getId());
	 }
        return "shop";
    }


	@GetMapping("/admin")
	public ResponseEntity<String> adminHome() {
		String homepg= "http://localhost:8086/admin";
		ResponseEntity<String> response = restTemplate.getForEntity(homepg, String.class);
		return response;
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
	 String cartpg="http://localhost:8008/cart";
	 @GetMapping("/cart")
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