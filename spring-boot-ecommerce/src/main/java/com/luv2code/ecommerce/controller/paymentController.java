package com.luv2code.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.ecommerce.service.PaymentService;

@Controller
@RequestMapping("payment")
public class paymentController {
	
	@Autowired
	PaymentService  paymentService  ;
	
	
	@PostMapping("/checkoutYourOrder")
    public ResponseEntity<String> checkoutOrder (@RequestBody Long  order_id) {
		
		
    	
    	 paymentService.checkoutOrder(order_id)  ;
     	String message =  paymentService.statusmessage ;

         return new ResponseEntity<>(message ,HttpStatus.OK);
    	
    	
    }

}
