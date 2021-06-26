package com.ecommerce.productcatalogservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.productcatalogservice.Service.CategoryService;
import com.ecommerce.productcatalogservice.Service.ProductService;
import com.ecommerce.productcatalogservice.global.GlobalData;

@Controller
public class ProductCatalogController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	RestTemplate restTemplate = new RestTemplate();
	
    @GetMapping({"/", "/home"})
    public String displayProducts(Model model)
    {
    	return "index";
    }
    
    @GetMapping("/shop")
    public String shopCategoriesandProducts(Model model)
    {
    	model.addAttribute("cartCount",GlobalData.cart.size());
    	model.addAttribute("categories",categoryService.getAllCategory());
    	model.addAttribute("products",productService.getAllProduct());
    	return "shop";
    
    }

    @GetMapping("/shop/category/{categoryId}")
    public String shopByCategory(Model model,@PathVariable Integer categoryId)
    {
    	model.addAttribute("cartCount",GlobalData.cart.size());
    	model.addAttribute("categories",categoryService.getAllCategory());
    	model.addAttribute("products",productService.getAllProductByCategory_id(categoryId));
    	return "shop";
    }  
    
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model,@PathVariable Long id)
    {
    	model.addAttribute("cartCount",GlobalData.cart.size());
    	model.addAttribute("product",productService.getProductById(id));
    	return "viewProduct";
    }  
    
    @GetMapping("/addToCart/{id}")
	public ResponseEntity<String> addToCart(@PathVariable Long id) {
	    ResponseEntity<String> responseCart = restTemplate.getForEntity("http://localhost:8008/addToCart/"+id, String.class);
    	return responseCart;
	}
	
	@GetMapping("/cart")
	public ResponseEntity<String> getCart()
	{
		ResponseEntity<String> responseCart = restTemplate.getForEntity("http://localhost:8008/cartService", String.class);
    	return responseCart;
	}


}
