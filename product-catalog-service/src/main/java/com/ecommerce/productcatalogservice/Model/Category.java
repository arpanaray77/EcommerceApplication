package com.ecommerce.productcatalogservice.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="categoryId")
	public Integer categoryId;
	
	@Column(name="categoryName",nullable=false,unique=true)
	private String categoryName;
	
	@Column(name="categoryType",nullable=false)
	private String categoryType;
	
	@Column(name="categorySection",nullable=false)
	private String categorySection;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getCategorySection() {
		return categorySection;
	}

	public void setCategorySection(String categorySection) {
		this.categorySection = categorySection;
	}
}
