package com.ecommerce.cartservice.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class Product {
 
	    @Id
	    @GeneratedValue (strategy = GenerationType.AUTO)
	    private Long id;

	    @NotNull
	    private String productName;
	    
	    @NotNull
	    private BigDecimal price;
	    
	    @NotNull
	    private String description;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="categoryId",referencedColumnName = "categoryId")
	    @NotNull
	    private Category category;

	    @NotNull
	    private int availability;
	    
	    @NotNull
	    private String imgName;

		public Product() {

		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		public int getAvailability() {
			return availability;
		}

		public void setAvailability(int availability) {
			this.availability = availability;
		}

		public String getImgName() {
			return imgName;
		}

		public void setImgName(String imgName) {
			this.imgName = imgName;
		}

}
