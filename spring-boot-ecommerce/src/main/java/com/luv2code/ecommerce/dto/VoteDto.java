package com.luv2code.ecommerce.dto;

import com.luv2code.ecommerce.entity.VoteType;

import lombok.Data;

@Data
public class VoteDto {
	
	private VoteType  voteType  ;
	
	private Long product_id  ;

	
}
