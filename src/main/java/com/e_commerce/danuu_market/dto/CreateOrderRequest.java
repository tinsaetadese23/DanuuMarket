package com.e_commerce.danuu_market.dto;

import lombok.Data;

import java.util.List;

@Data
public
class CreateOrderRequest {

    private double total_price;
    private String status;
    private int quantity;
    private List<OrderProductRequest> products;

    // You can add more fields if needed, like user information, shipping address, etc.
}

