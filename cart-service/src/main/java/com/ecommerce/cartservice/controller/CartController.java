package com.ecommerce.cartservice.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.cartservice.Repository.CartRepository;
import com.ecommerce.cartservice.Services.CartService;
import com.ecommerce.cartservice.Services.ProductService;
import com.ecommerce.cartservice.model.CartItem;

@Controller
public class CartController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartService cartService;
	
	String homepg= "http://localhost:8086/shop";
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> response = restTemplate.getForEntity(homepg, String.class);
	
	@GetMapping("/addToCart/{userId}/{productId}")
	public ResponseEntity<String> addToCart(@PathVariable int userId,@PathVariable Long pid) {
		cartService.addToCart(pid, userId,1);
		return response;
	}
	
	@GetMapping("/")
	public ResponseEntity<String> getResponse() {
		return response;
	}
	
	
	@GetMapping("/shop/viewproduct/{id}")
    public ResponseEntity<String> viewProduct(@PathVariable Long id)
    {
	    ResponseEntity<String> responseView = restTemplate.getForEntity(homepg +"/viewproduct/"+id, String.class);
    	return responseView;
    }
	
	@GetMapping("/cartService/{userId}")
	public String showCart(@PathVariable int userId,Model model)
	{
		List<CartItem> cartt=cartRepository.findByUserId(userId);
		model.addAttribute("cartCount",cartt.size());
		model.addAttribute("total",cartt.stream().map(x->x.getProduct().getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add));
		model.addAttribute("cart",cartt);
		return "cartService";
	}
	
	@GetMapping("/cartService/removeItem/{userId}/{productId}")
	public String removeCartItem(@PathVariable int userId,@PathVariable Long productId)
	{
		cartService.removeFromCart(productId, userId);
		return "redirect:/cartService";
	}
	
	@GetMapping("/checkout/{userId}")
	public String goCheckout(@PathVariable int userId,Model model)
	{
		List<CartItem> cartt=cartRepository.findByUserId(userId);
		model.addAttribute("total",cartt.stream().map(x->x.getProduct().getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add));
		return "checkout";
	}
}
