package com.ecommerce.productcatalogservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import com.ecommerce.productcatalogservice.Service.CategoryService;
import com.ecommerce.productcatalogservice.Service.ProductService;



@Controller
public class ProductCatalogController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	RestTemplate restTemplate = new RestTemplate();
	//create or get cart
	String url ="http://localhost:8008/create";
	//Product[] cart = restTemplate.getForObject(url, Product[].class);

	
    @GetMapping({"/", "/home"})
    public String displayProducts(Model model)
    {
    	return "index";
    }
    
    @GetMapping("/shop")
    public String shopCategoriesandProducts(Model model)
    {
    	//model.addAttribute("cartCount",cart.length);
    	model.addAttribute("categories",categoryService.getAllCategory());
    	model.addAttribute("products",productService.getAllProduct());
    	return "shop";
    
    }

    @GetMapping("/shop/category/{categoryId}")
    public String shopByCategory(Model model,@PathVariable Integer categoryId)
    {
    	//model.addAttribute("cartCount",cart.length);
    	model.addAttribute("categories",categoryService.getAllCategory());
    	model.addAttribute("products",productService.getAllProductByCategory_id(categoryId));
    	return "shop";
    }  
    
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model,@PathVariable Long id)
    {
    	//model.addAttribute("cartCount",cart.length);
    	model.addAttribute("product",productService.getProductById(id));
    	return "viewProduct";
    }  

}
