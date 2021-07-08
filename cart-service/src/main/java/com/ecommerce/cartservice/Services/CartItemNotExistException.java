package com.ecommerce.cartservice.Services;

public class CartItemNotExistException extends Exception {

	public CartItemNotExistException(String message) {
	  super(message);
	}

}
