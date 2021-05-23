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
	
	public List<Product> getAllProduct() {
		return productRespository.findAll();
	}

	@Override
	public java.util.Optional<Product> getProductById(Long id) {
	    return productRespository.findById(id);
	}

	@Override
	public void addProduct(Product product) {
	   productRespository.save(product);
	}

	@Override
	public void deleteProductById(Long productId) {
	   productRespository.deleteById(productId);
	}

	@Override
	public List<Product> getAllProductByCategoryId(Integer categoryId) {
		return productRespository.findAllByCategoryId(categoryId);
	}

}
