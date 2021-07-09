package com.ecommerce.cartservice.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ecommerce.cartservice.Repository.ProductRepository;
import com.ecommerce.cartservice.model.Product;


@Service
@Transactional
@EnableTransactionManagement 
public class ProductServiceImpl implements ProductService {
    
	@Autowired
	public ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}


	@Override
	public Product getProductById(Long id) {
		return productRepository.getById(id);
	}

	@Override
	public void addProduct(Product product) {
	    productRepository.save(product);
	}

	@Override
	public void deleteProductById(Long productId) {
	    productRepository.deleteById(productId);
	}

	@Override
	public List<Product> getAllProductByCategory_id(Integer categoryId) {
		return productRepository.findAllProductsByCategoryCategoryId(categoryId);
	}

}