package com.ecommerce.cartservice.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.cartservice.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	 public List<Product> findAll();
	 
	public List<Product> findAllProductsByCategoryCategoryId(Integer category_id);
}
