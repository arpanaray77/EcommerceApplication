package com.ecommerce.productcatalogservice.Service;

import java.util.List;
import com.ecommerce.productcatalogservice.Model.Product;

public interface ProductService {
	
	public List<Product> getAllProduct();
	//public List<Product> getAllProductByCategory(String category);
	public List<Product> getAllProductByCategoryId(Integer categoryId);
	public java.util.Optional<Product> getProductById(Long id);
	public void addProduct(Product product);
    public void deleteProductById(Long productId);
}
