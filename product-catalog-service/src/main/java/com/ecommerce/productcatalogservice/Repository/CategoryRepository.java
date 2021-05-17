package com.ecommerce.productcatalogservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.productcatalogservice.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public List<Category> findAll();

	public Category save(Category category);
}
