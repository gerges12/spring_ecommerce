package com.luv2code.ecommerce.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luv2code.ecommerce.errors.ErrorResponse;
import com.luv2code.ecommerce.exceptions.PermissionException;
import com.luv2code.ecommerce.exceptions.ProductNotFoundException;

@ControllerAdvice
public class PermissionExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleEcxeption(PermissionException exc ){
		
		ErrorResponse error = new ErrorResponse()  ;
		
		error.setStatus(HttpStatus.UNAUTHORIZED.value());

		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		

		
		return new ResponseEntity <> (error , HttpStatus.UNAUTHORIZED)  ;
		
	}
	
	

}
