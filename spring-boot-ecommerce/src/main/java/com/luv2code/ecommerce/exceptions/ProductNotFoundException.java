package com.luv2code.ecommerce.exceptions;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String message ) {
		super(message)  ;
	}
}
