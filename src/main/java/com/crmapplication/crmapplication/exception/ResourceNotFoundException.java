package com.crmapplication.crmapplication.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException (String message){
        super(message);
    }
}
