package com.luv2code.ecommerce.entity;

import static javax.persistence.FetchType.LAZY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="Ordersupportofproduct")
@Data
public class Ordersupportofproduct {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ordersupportofproduct_id")
     private Long id;
 
	
    @ManyToOne(fetch = LAZY)  
	@JoinColumn(name = "SupportorderId")
	@JsonIgnore
    private SupportOrder supportorderId;
 
	@JsonIgnoreProperties({"imageUrl"  ,"comments","description" ,"likes" ,"dislikes","lastUpdated" ,"dateCreated", "sku" , "category" , "product_id" , "active" , "views" , "orderProduct" , "unitsInStock" })
    @ManyToOne()
    private Product product;
 
    @Column(name = "quantity")
    private int quantity ;

}
