package com.ecommerce.productcatalogservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.productcatalogservice.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	public List<Category> findAll();

}
