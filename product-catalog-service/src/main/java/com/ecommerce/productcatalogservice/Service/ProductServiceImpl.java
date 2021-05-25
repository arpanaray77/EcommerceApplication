package com.ecommerce.productcatalogservice.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.productcatalogservice.Model.Product;
import com.ecommerce.productcatalogservice.Repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    
	@Autowired
	public ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}


	@Override
	public Product getProductById(Long id) {
		return productRepository.getOne(id);
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