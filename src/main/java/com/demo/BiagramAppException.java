package com.demo;

public class BiagramAppException extends RuntimeException {
    public BiagramAppException(String message, Throwable t){
        super(message,t);
    }
}
