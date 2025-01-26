package com.e_commerce.danuu_market.Exceptions;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message){
        super(message);
    }
}
