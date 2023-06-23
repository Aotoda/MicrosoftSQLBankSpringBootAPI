package com.example.bank.controller;

import java.util.NoSuchElementException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.bank.model.DTOs.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleNFException(HttpServletRequest req, NumberFormatException ex){
 
        String error = "Unable to submit post: " + ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));

    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> noSuchElementException(HttpServletRequest req, NoSuchElementException ex){
        
        String error = "The row for address is not existent: " + req.getRequestURI();
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND, error));

    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorRes){
        return new ResponseEntity<Object>(errorRes, errorRes.getStatus());
    }

}
    