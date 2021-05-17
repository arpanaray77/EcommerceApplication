package com.ecommerce.productcatalogservice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminProductController {

	@GetMapping("/admin")
	public String adminHome() {
		return "admin";
	}

	@GetMapping("/admin/categories")
	public String categoriesList() {
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String addCategories() {
		return "categoriesAdd";
	}

}