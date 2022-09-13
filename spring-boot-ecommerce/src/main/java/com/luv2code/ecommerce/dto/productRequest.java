package com.luv2code.ecommerce.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data

public class productRequest {
	
	
   
    private Long id;

    private String sku;

    private String name;

    private String description;

    private String categoryName;

    
    private int unitPrice;

    private String imageUrl;

    private boolean active;

    private int unitsInStock;

   
	
	
	

}
