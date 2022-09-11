package com.luv2code.ecommerce.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luv2code.ecommerce.errors.ErrorResponse;
import com.luv2code.ecommerce.exceptions.EmailAlreadyExistException;

@ControllerAdvice
public class AuthHandler {
	
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleEcxeption(EmailAlreadyExistException exc ){
		
		ErrorResponse error = new ErrorResponse()  ;
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());

		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		

		
		return new ResponseEntity <> (error , HttpStatus.BAD_REQUEST)  ;
		
	} 

}
