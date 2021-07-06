package com.ecommerce.cartservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.cartservice.model.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Integer>{
     
	 List<CartItem> findAllByUserIdOrderByCreatedDateDesc(Integer userId);
}
