package com.ecommerce.cartservice.controller.DTO;

import com.ecommerce.cartservice.model.CartItem;
import com.sun.istack.NotNull;

public class AddtoCartDto {
    private Integer id;
    private @NotNull Integer userId;
    private @NotNull Long productId;
    private @NotNull Integer quantity;

    public AddtoCartDto() {
    }

    public AddtoCartDto(Integer id, @NotNull Integer userId, @NotNull Long productId, @NotNull Integer quantity) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public AddtoCartDto(CartItem cart) {
        this.setId(cart.getId());
        this.setProductId(cart.getProductId());
        this.setUserId(cart.getUserId());
        this.setQuantity(cart.getQuantity());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ",";
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
