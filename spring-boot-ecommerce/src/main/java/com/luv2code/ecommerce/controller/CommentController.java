package com.luv2code.ecommerce.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.dto.CommentRequest;
import com.luv2code.ecommerce.dto.CommentresponseDto;
import com.luv2code.ecommerce.dto.orderRequest;
import com.luv2code.ecommerce.service.CommentService;
import com.luv2code.ecommerce.service.orderService;

@RestController
@RequestMapping("comment")
public class CommentController {
	
	@Autowired
	CommentService  commentService  ;  
	
	@PostMapping("/createcomment")
    public ResponseEntity<Void> createProduct (@RequestBody CommentRequest  commentRequest) {
		
		
		commentService.save(commentRequest)  ;
		
        return new ResponseEntity<>(CREATED)  ;

    	
    	
    }
	
	
	
	
	 @GetMapping("/by-product/{postId}")
	    public ResponseEntity<List<CommentresponseDto>> getAllCommentsForPost(@PathVariable Long postId) {
	        return ResponseEntity.status(OK)
	                .body(commentService.getAllCommentsForPost(postId));
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
