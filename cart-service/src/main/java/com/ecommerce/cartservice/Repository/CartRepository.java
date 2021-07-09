package com.ecommerce.cartservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.cartservice.model.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Integer>{
     
	 List<CartItem> findByUserId(Integer userId);
	 CartItem findByUserIdAndProductId(Integer userId,Long productId);
	 public void deleteByUserIdAndProductId(Integer userId,Long productId);
}
