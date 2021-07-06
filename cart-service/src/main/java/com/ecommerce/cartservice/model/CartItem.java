package com.ecommerce.cartservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.ecommerce.cartservice.controller.DTO.AddtoCartDto;
import com.ecommerce.cartservice.controller.DTO.CartDto;

@Entity
@Table(name="cartitem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    @NotBlank(message = "{userId.not-null}")
    private Integer userId;

    @Column(name = "product_id")
    @NotBlank(message = "{productId.not-null}")
    private Long productId;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;


    private Integer quantity;

    public CartItem() {
    }


    public CartItem(CartDto cartDto, Product product,int userId){
        this.userId = userId;
        this.productId=cartDto.getProduct().getId();
        this.quantity=cartDto.getQuantity();
        this.product = product;
        this.createdDate = new Date();
    }

    public CartItem (@NotBlank Integer userId, @NotBlank Long productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.createdDate = new Date();
        this.quantity = quantity;
    }

    public CartItem(CartDto cartDto, Product product) {
        this.productId = cartDto.getProduct().getId();
        this.quantity = cartDto.getQuantity();
        this.product = product;
        this.createdDate = new Date();
    }


	public CartItem(AddtoCartDto addtoCartDto,int userId) {
		this.id=addtoCartDto.getId();
		this.userId=addtoCartDto.getUserId();
		this.productId=addtoCartDto.getProductId();
		this.quantity=addtoCartDto.getQuantity();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
}