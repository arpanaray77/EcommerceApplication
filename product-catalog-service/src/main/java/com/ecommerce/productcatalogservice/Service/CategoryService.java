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
	
	public void addCategory(Category category)
	{
	  categoryRepository.save(category);
	}
	
	public void deleteCategoryById(int id)
	{
	  categoryRepository.deleteById(id);
	}
	
	public java.util.Optional<Category> updateCategoryById(int id)
	{
	  return categoryRepository.findById(id);
	}
		
	public List<Category> getAllCategory()
	{
		return categoryRepository.findAll();
	}
}
