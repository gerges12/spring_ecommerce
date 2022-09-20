package com.luv2code.ecommerce.dto;

import com.luv2code.ecommerce.entity.Statusofrequest;

import lombok.Data;

@Data
public class reply_for_support_request {
	
//	SupportRequestDTO{}

    private Statusofrequest status_of_reply ;

	private Long support_order_id  ;


}
