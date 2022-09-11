package com.luv2code.ecommerce.errors;

import java.math.BigDecimal;
import java.util.Date;

import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;

import lombok.Data;

@Data

public class ErrorResponse {
	
	private int status ;
	private String message ;
	private long timestamp ;
		
	
	
	

}
