package com.luv2code.ecommerce.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luv2code.ecommerce.errors.ErrorResponse;
import com.luv2code.ecommerce.exceptions.voteException;

@ControllerAdvice
public class VoteExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleEcxeption(voteException exc ){
		
		ErrorResponse error = new ErrorResponse()  ;
		
		error.setStatus(HttpStatus.NOT_FOUND.value());

		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		

		
		return new ResponseEntity <> (error , HttpStatus.NOT_FOUND)  ;
		
	}


}
