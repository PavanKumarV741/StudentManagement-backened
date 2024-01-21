package com.crud.crudappbackened.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorResponses> handleResourceNotFoundException(ResourceNotFoundException ex) {
        CustomErrorResponses errorResponse = new CustomErrorResponses();
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponses> handleEmailAlreadyExists(StudentAlreadyExistsException ex){
    	CustomErrorResponses errorResponse = new CustomErrorResponses();
    	errorResponse.setMessage(ex.getMessage());
    	
    	return new ResponseEntity(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(InvalidAge.class)
    public ResponseEntity<CustomErrorResponses> handleInvalidAge(InvalidAge ex){
    	CustomErrorResponses errorResponse = new CustomErrorResponses();
    	errorResponse.setMessage(ex.getMessage());
    	return new ResponseEntity(errorResponse,HttpStatus.NOT_ACCEPTABLE);
    }

    // Handle other exceptions similarly
}
