package com.crmapplication.crmapplication.exception;

import com.crmapplication.crmapplication.util.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> ResourceNotFoundException(ResourceNotFoundException ex){

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        Error error = new Error(ex.getMessage(), httpStatus);
        return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
