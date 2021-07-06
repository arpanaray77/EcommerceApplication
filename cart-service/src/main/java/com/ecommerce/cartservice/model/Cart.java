package com.ecommerce.cartservice.model;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.cartservice.controller.DTO.CartDto;

public class Cart {
    private List<CartDto> cartItems;
    private BigDecimal totalCost;

    public Cart(List<CartDto> cartDtoList, BigDecimal totalCost) {
        this.cartItems = cartDtoList;
        this.totalCost = totalCost;
    }

    public List<CartDto> getcartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartDto> cartDtoList) {
        this.cartItems = cartDtoList;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}