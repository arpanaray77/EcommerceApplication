package com.ecommerce.productcatalogservice.Service;

import java.util.List;
import com.ecommerce.productcatalogservice.Model.Product;

public interface ProductService {
	
	public List<Product> getAllProduct();
	public List<Product> getAllProductByCategory(String category);
	public Product getProductById(Long id);
	public List<Product> getAllProductByName(String name);
	public Product addProduct(Product product);
	public void deleteProduct(Long productId);
}
