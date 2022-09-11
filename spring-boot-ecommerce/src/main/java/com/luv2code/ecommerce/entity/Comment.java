package com.luv2code.ecommerce.entity;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="comment")
@Data
public class Comment {

    	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "comment_id")
	    private Long comment_id;
    	 
    	 
 	    @Column(name = "content")
        private String content ;
 	    
 		@JsonIgnore
 	    @ManyToOne()  
 	    @JoinColumn(name = "productId" )
 	    private Product product;
 	    
 		@JsonIgnore
 		@ManyToOne(fetch = LAZY)  
	    @JoinColumn(name = "userId")
	    private User user;
 		
 		
 		public String toString(){//overriding the toString() method  
 			  return content+" "+product+" ";  
 			 }  
    	 
	
}
