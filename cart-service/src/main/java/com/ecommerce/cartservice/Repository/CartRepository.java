package com.ecommerce.cartservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.cartservice.model.CartItem;
import com.ecommerce.cartservice.model.Product;

public interface CartRepository extends JpaRepository<CartItem, Integer>{
     
	 List<CartItem> findByUserId(Integer userId);
	 public void deleteByUserIdAndProductId(Integer userId,Long productId);
	 Product updateQuantity(int quantity,Integer userId,Long productId);
}
