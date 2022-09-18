package com.luv2code.ecommerce.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.dto.CommentRequest;
import com.luv2code.ecommerce.dto.FinancialSupportRequestdto;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.SupportOrder;
import com.luv2code.ecommerce.service.SupportService;

@RestController
@RequestMapping("sponser")
public class supportController {
	
	@Autowired
	SupportService supportService  ;
	
	@PostMapping("/support_request")
    public SupportOrder createSupportRequest (@RequestBody FinancialSupportRequestdto  supportRequestdto) {
		
		
	return	supportService.save(supportRequestdto)  ;
		}
	
	
	@DeleteMapping("/delete_your_support_request/{id}")
	public ResponseEntity<String> deleteYourSupportRequest(@PathVariable("id") Long SupportRequestId)
	
	{
		supportService.deleteSr(SupportRequestId)  ;
		String message =  supportService.statusmessage ;
        return new ResponseEntity<>(message ,HttpStatus.ACCEPTED);

	}
	
	  @GetMapping("/your_support_request")
	    public List<SupportOrder>  get_support_request() {
	    	return supportService.support_requests() ;
	    } 
	  
	  @GetMapping("/requests_for_reply")
	    public List<SupportOrder>  get_requests_for_reply() {
	    	return supportService.requests_for_reply() ;
	    } 
	
	
	

	
	
}
