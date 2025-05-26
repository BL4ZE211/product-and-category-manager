package com.productmanager.api;

public class NotFoundException extends RuntimeException{

    NotFoundException(String msg){
        super(msg);
    }
}
