package com.ecommerce.cartservice.controller;

import java.util.function.ToDoubleFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.cartservice.Services.ProductService;
import com.ecommerce.cartservice.global.GlobalData;
import com.ecommerce.cartservice.model.Product;

@Controller
public class CartController {

	@Autowired
	ProductService productService;
	
	Product product=new Product();
	
	String homepg= "http://localhost:8086/shop";
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> response = restTemplate.getForEntity(homepg, String.class);
	
	@GetMapping("/addToCart/{id}")
	public ResponseEntity<String> addToCart(@PathVariable Long id) {
		GlobalData.cart.add(productService.getProductById(id));
		System.out.println(GlobalData.cart);
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
	
	@GetMapping("/cartService")
	public String getCart(Model model)
	{
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble((ToDoubleFunction<? super Product>) product.getPrice()).sum());
		model.addAttribute("cart",GlobalData.cart.get(0));
		return "cartService";
	}
}
