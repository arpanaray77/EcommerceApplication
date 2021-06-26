package com.ecommerce.cartservice.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.cartservice.model.Product;


@Service
public interface ProductService {
	
	public List<Product> getAllProduct();
	public List<Product> getAllProductByCategory_id(Integer categoryId);
	public Product getProductById(Long id);
	public void addProduct(Product product);
    public void deleteProductById(Long productId);

}
