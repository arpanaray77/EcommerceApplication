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
	public ProductRepository productRespository;
	
	@Override
	public List<Product> getAllProduct() {
		return productRespository.findAll();
	}

	@Override
	public List<Product> getAllProductByCategory(String category) {
		return productRespository.findAllByCategory(category);
	}

	@Override
	public Product getProductById(Long id) {
	    return productRespository.getOne(id);
	}

	@Override
	public List<Product> getAllProductByName(String name) {
		return productRespository.findAllByProductName(name);
	}

	@Override
	public Product addProduct(Product product) {
	  return productRespository.save(product);
	}

	@Override
	public void deleteProduct(Long productId) {
	   productRespository.deleteById(productId);
	}

}
