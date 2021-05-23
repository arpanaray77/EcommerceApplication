package com.ecommerce.productcatalogservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.productcatalogservice.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	public List<Product> findAllByCategoryId(Integer categoryId);
}
