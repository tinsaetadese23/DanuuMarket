package com.e_commerce.danuu_market.Exceptions;

public class ProductAlreadyExists extends RuntimeException{
    public ProductAlreadyExists(String message){
        super(message);
    }
}
