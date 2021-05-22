package com.ecommerce.productcatalogservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.productcatalogservice.Service.CategoryService;
import com.ecommerce.productcatalogservice.Service.ProductService;

@Controller
public class ProductCatalogController {
	
	@Autowired
	public CategoryService categoryService;
	
	@Autowired
	public ProductService productService;
	
    @GetMapping({"/", "/home"})
    public String displayProducts(Model model)
    {
    	return "index";
    }
    
    @GetMapping({"/shop"})
    public String shopCategoriesandProducts(Model model)
    {
    	model.addAttribute("categories",categoryService.getAllCategory());
    	model.addAttribute("products",productService.getAllProduct());
    	return "index";
    
    }

}
