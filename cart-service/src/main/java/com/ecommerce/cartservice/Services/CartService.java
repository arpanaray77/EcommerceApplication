package com.ecommerce.cartservice.Services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.ecommerce.cartservice.Repository.CartRepository;
import com.ecommerce.cartservice.controller.DTO.AddtoCartDto;
import com.ecommerce.cartservice.controller.DTO.CartDto;
import com.ecommerce.cartservice.model.Cart;
import com.ecommerce.cartservice.model.CartItem;
import com.ecommerce.cartservice.model.Product;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddtoCartDto addtoCartDto,int userId){
        CartItem cart = getAddtoCartFromDto(addtoCartDto,userId);
        cartRepository.save(cart);
    }

    private CartItem getAddtoCartFromDto(AddtoCartDto addtoCartDto, int userId) {
    	 CartItem cart = getAddtoCartFromDto(addtoCartDto,userId);
    	 return cart;
	}

    public Cart listCartItems(int user_id) {
        List<CartItem> cartList = cartRepository.findAllByUserIdOrderByCreatedDateDesc(user_id);
        List<CartDto> cartItems = new ArrayList<>();
        for (CartItem cart:cartList){
            CartDto cartDto = getDtoFromCartItem(cart);
            cartItems.add(cartDto);
        }
        BigDecimal totalCost=new BigDecimal(0); 
        for (CartDto cartDto:cartItems){
        	BigDecimal quantity = BigDecimal.valueOf(cartDto.getQuantity());
            totalCost= totalCost.add(cartDto.getProduct().getPrice().multiply(quantity));
        }
        Cart cartCost = new Cart(cartItems,totalCost);
        return cartCost;
    }

	public static CartDto getDtoFromCartItem(CartItem cart) {
        CartDto cartDto = new CartDto(cart);
        return cartDto;
    }


    public void updateCartItem(AddtoCartDto cartDto,int userId,Product product){
        CartItem cart = getAddtoCartFromDto(cartDto,userId);
        cart.setQuantity(cartDto.getQuantity());
        cart.setUserId(userId);
        cart.setId(cartDto.getId());
        cart.setProductId(product.getId());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    public void deleteCartItem(int id,int userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);

    }


}
