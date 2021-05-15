package com.ecommerce.productcatalogservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.productcatalogservice.Model.Product;
import com.ecommerce.productcatalogservice.Service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	public ProductService productService;
	
	@GetMapping(value = "/getProductList")
	 public List<Product> getAllProducts(){
        List<Product> products =  productService.getAllProduct();
        return products;
    }
	
	@GetMapping(value = "/products", params = "category")
    public List<Product> getAllProductByCategory(@RequestParam ("category") String category){
        List<Product> products = productService.getAllProductByCategory(category);
        return products;
    }

    @GetMapping (value = "/products/{id}")
    public Product getOneProductById(@PathVariable ("id") long id){
        Product product =  productService.getProductById(id);
       return product;
    }

    @GetMapping (value = "/products", params = "name")
    public List<Product> getAllProductsByName(@RequestParam ("name") String name){
        List<Product> products =  productService.getAllProductByName(name);
       return products;
    }

}
