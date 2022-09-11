package com.luv2code.ecommerce.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luv2code.ecommerce.errors.ErrorResponse ;
import com.luv2code.ecommerce.exceptions.ProductAlreadyExistException;
import com.luv2code.ecommerce.exceptions.ProductNotFoundException;
import com.luv2code.ecommerce.exceptions.ProductNotHaingCommentsException;

@ControllerAdvice
public class ProductRestExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleEcxeption(ProductNotFoundException exc ){
		
		ErrorResponse error = new ErrorResponse()  ;
		
		error.setStatus(HttpStatus.NOT_FOUND.value());

		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		

		
		return new ResponseEntity <> (error , HttpStatus.NOT_FOUND)  ;
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleEcxeption(ProductNotHaingCommentsException exc ){
		
		ErrorResponse error = new ErrorResponse()  ;
		
		error.setStatus(HttpStatus.NOT_FOUND.value());

		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		

		
		return new ResponseEntity <> (error , HttpStatus.NOT_FOUND)  ;
		
	}
	
	
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleEcxeption(ProductAlreadyExistException exc ){
		
            ErrorResponse error = new ErrorResponse()  ;
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());

		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		

		
		return new ResponseEntity <> (error , HttpStatus.NOT_FOUND)  ;
		
	}
}
