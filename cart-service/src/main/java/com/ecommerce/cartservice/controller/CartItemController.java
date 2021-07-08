package com.ecommerce.cartservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cartservice.Services.CartItemNotExistException;
import com.ecommerce.cartservice.Services.CartService;
import com.ecommerce.cartservice.Services.ProductService;
import com.ecommerce.cartservice.controller.DTO.AddtoCartDto;
import com.ecommerce.cartservice.exceptions.AuthenticationFailException;
import com.ecommerce.cartservice.model.Cart;
import com.ecommerce.cartservice.model.Product;

@RestController
@RequestMapping("/cart")
public class CartItemController {
	
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddtoCartDto addToCartDto,@RequestParam("token") String token) throws AuthenticationFailException {
     
        int userId =Integer.parseInt(token);

        cartService.addToCart(addToCartDto,userId);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);

    }
    
    
    @GetMapping("/")
    public ResponseEntity<Cart> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
      
        int userId = Integer.parseInt(token);
        Cart cartCost = cartService.listCartItems(userId);
        return new ResponseEntity<Cart>(cartCost,HttpStatus.OK);
    }
    
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddtoCartDto cartDto,
                                                      @RequestParam("token") String token) throws AuthenticationFailException{

        Product product = productService.getProductById(cartDto.getProductId());
        int userId = Integer.parseInt(token);
        cartService.updateCartItem(cartDto,userId,product);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID,@RequestParam("token") String token) throws CartItemNotExistException {
        

    	 int userId = Integer.parseInt(token);
    	 cartService.deleteCartItem(itemID, userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }

}