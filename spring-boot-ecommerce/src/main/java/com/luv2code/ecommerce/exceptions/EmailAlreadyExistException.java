package com.luv2code.ecommerce.exceptions;

public class EmailAlreadyExistException extends RuntimeException {

	public EmailAlreadyExistException(String message ) {
		super(message)  ;
	}
}
