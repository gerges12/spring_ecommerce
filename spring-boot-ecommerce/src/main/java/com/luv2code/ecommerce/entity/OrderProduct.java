package com.luv2code.ecommerce.entity;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity (name = "Orderproduct")
@Table(name = "order_product")

public class OrderProduct {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderproduct_id")

    private Long id;
 
	
    @ManyToOne(fetch = LAZY)  
	@JoinColumn(name = "orderId")
	@JsonIgnore
    private Order order;
 
	@JsonIgnoreProperties({"imageUrl"  ,"comments","description" ,"lastUpdated" ,"dateCreated", "sku" , "category" , "product_id" , "active" , "views" , "orderProduct" , "unitsInStock" })
    @ManyToOne()
    private Product product;
 
    @Column(name = "quantity")
    private int quantity ;
 

}
