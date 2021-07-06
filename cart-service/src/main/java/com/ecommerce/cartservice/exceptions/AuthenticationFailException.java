package com.ecommerce.cartservice.exceptions;

@SuppressWarnings("serial")
public class AuthenticationFailException extends IllegalArgumentException {
    public AuthenticationFailException(String message) {
        super(message);
    }
}
