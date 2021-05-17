package com.ecommerce.productcatalogservice.Service;

import org.springframework.stereotype.Service;

import com.ecommerce.productcatalogservice.Model.Category;
import com.ecommerce.productcatalogservice.Repository.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public Category addCategory(Category category)
	{
		return categoryRepository.save(category);
	}
	
	public List<Category> getAllCategory()
	{
		return categoryRepository.findAll();
	}
}
