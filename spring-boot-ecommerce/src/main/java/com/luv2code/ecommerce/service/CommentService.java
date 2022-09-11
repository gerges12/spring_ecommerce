package com.luv2code.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luv2code.ecommerce.dao.CommentRepository;
import com.luv2code.ecommerce.dao.ProductRepository;
import com.luv2code.ecommerce.dao.UserRepository;
import com.luv2code.ecommerce.dto.CommentRequest;
import com.luv2code.ecommerce.dto.CommentresponseDto;
import com.luv2code.ecommerce.entity.Comment;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.exceptions.ProductNotFoundException;
import com.luv2code.ecommerce.exceptions.ProductNotHaingCommentsException;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository   commentrepository  ;
	

	@Autowired
	AuthService  authService  ;
	
	@Autowired 
	ProductRepository  productRepository  ;
	
	@Autowired 
    UserRepository   userRepository  ;

	public void save(CommentRequest commentRequest) {
		
		Comment comment = new Comment () ; 
		
		
		Product product = productRepository.findById(commentRequest.getProduct_id())
                .orElseThrow(  () -> new ProductNotFoundException("product id not found " ) ) ;
		
		
         User  user = authService.getCurrentUser()  ;
         
        

		
		comment.setContent(commentRequest.getContent());
		comment.setUser(authService.getCurrentUser());
		
		comment.setProduct(product);
		
		
		
		comment = commentrepository.save(comment)	;
		
      
		 
		 user.getComments().add(comment )  ;
		 userRepository.save(user)  ;
		
	}

	public List<CommentresponseDto> getAllCommentsForPost(Long productId) {
		
		List<CommentresponseDto> commentsresponse = new ArrayList<CommentresponseDto> () ;
		
		Product product = productRepository.findById(productId)
                .orElseThrow(  () -> new ProductNotFoundException("product id not found " ) ) ;
		
		List<Comment> comments = commentrepository.findByProduct(product)  ;
		

		if (comments.isEmpty()) {
			System.out.println("gggg");

			throw new ProductNotHaingCommentsException("this product havnot any comments")  ;
		}
		
		for (Comment c:comments) {
			
			CommentresponseDto   commentresponseDto =  new CommentresponseDto ()  ;
			commentresponseDto.setContent(c.getContent());
			commentresponseDto.setUsername(c.getUser().getUsername());
			
			commentsresponse.add(commentresponseDto)  ;
		}
		
		return commentsresponse;
	}


}






















