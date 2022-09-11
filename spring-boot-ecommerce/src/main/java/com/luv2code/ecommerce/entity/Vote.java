package com.luv2code.ecommerce.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Entity
@Table(name="vote")
@Data
public class Vote {
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long voteId;
    
     @Enumerated(EnumType.STRING)
     @Column(name = "voteType") 
     private VoteType voteType;
    
    
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "productId")
    private Product product;
    
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;
}
