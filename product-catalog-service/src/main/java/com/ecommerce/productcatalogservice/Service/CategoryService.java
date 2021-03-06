package com.ecommerce.productcatalogservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.productcatalogservice.Model.Category;
import com.ecommerce.productcatalogservice.Repository.CategoryRepository;

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
	
	public java.util.Optional<Category> getCategoryById(int id)
	{
	  return categoryRepository.findById(id);
	}
		
	public List<Category> getAllCategory()
	{
		return categoryRepository.findAll();
	}
}
