package com.ecommerce.productcatalogservice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminProductController {

	@GetMapping("/admin")
	public String adminHome() {
		return "admin";
	}

	@GetMapping("/admin/homepage")
	public String home() {
		return "homepg";
	}

}