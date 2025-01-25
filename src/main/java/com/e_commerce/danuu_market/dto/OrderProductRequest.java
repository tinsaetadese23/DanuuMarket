package com.e_commerce.danuu_market.dto;

import lombok.Data;

@Data
public class OrderProductRequest {
    private int productId;
    private int quantity;
}
