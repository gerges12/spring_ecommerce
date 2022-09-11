package com.luv2code.ecommerce.exceptions;

public class ProductAlreadyExistException extends RuntimeException {
	
	public ProductAlreadyExistException(String message ) {
		super(message)  ;
	}

}
