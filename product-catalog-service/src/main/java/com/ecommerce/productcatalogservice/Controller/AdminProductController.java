package com.ecommerce.productcatalogservice.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.productcatalogservice.Model.Product;
import com.ecommerce.productcatalogservice.Service.ProductService;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;
    
    @PostMapping(value = "/products")
    private Product addProduct(@RequestBody Product product, HttpServletRequest request){
    	if(product != null) {
    		try {
    			productService.addProduct(product);
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	 return product;
    }
    
    @DeleteMapping(value = "/products/{id}")
    private void  deleteProduct(@PathVariable("id") Long id){
    	Product product = productService.getProductById(id);
    	if(product != null) {
    		try {
    			productService.deleteProduct(id);
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	}      
    }
}