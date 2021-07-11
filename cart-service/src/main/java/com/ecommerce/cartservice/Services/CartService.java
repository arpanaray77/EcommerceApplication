package com.ecommerce.cartservice.Services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.cartservice.Repository.CartRepository;
import com.ecommerce.cartservice.model.CartItem;

@Service
@Transactional
public class CartService {

    private  CartRepository cartRepository;
    @Autowired
	ProductService productService;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    } 

    public void addToCart(Long productId,int userId,int quantity){
        CartItem cartItem = new CartItem(userId,productId,productService.getProductById(productId),quantity);
        cartRepository.save(cartItem);
    }
    
    public void removeFromCart(int userId,Long productId){
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }
    
}
