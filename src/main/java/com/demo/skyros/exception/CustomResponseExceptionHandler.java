package com.demo.skyros.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        AppResponse appResponse = new AppResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getDescription(false));
        ResponseEntity responseEntity = new ResponseEntity(appResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        AppResponse appResponse = new AppResponse(new Date(), HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false));
        ResponseEntity responseEntity = new ResponseEntity(appResponse, HttpStatus.OK);
        return responseEntity;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        AppResponse appResponse = new AppResponse(new Date(), HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getBindingResult().getFieldError().getDefaultMessage());
        ResponseEntity responseEntity = new ResponseEntity(appResponse, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
