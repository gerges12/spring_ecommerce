package com.luv2code.ecommerce.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.dto.CommentRequest;
import com.luv2code.ecommerce.dto.SupportRequestdto;
import com.luv2code.ecommerce.service.SponserService;

@RestController
@RequestMapping("sponser")
public class sponserController {
	
	@Autowired
	SponserService sponserservice  ;
	
	@PostMapping("/support_request")
    public ResponseEntity<Void> createSupportRequest (@RequestBody SupportRequestdto  supportRequestdto) {
		
		
		sponserservice.save(supportRequestdto)  ;
		
        return new ResponseEntity<>(CREATED)  ;

    	
    	
    }

	
	
}
