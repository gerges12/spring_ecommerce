package com.luv2code.ecommerce.entity;

import static javax.persistence.FetchType.LAZY;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



import lombok.Data;

@Entity (name = "SupportOrder")
@Table(name = "SupportOrder")

@Data
public class SupportOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupportOrder_id")
    private Long id;
 
	  @Enumerated(EnumType.STRING)
	  @Column(name = "status_of_request")
	  private Statusofrequest statusofrequest ;
	  
	 
	
    @JsonIgnoreProperties({"userId" ,"comments" ,"votes" ,"products" , "roles" ,"password" ,"email" , "created","enabled" ,"total_of_orders" , "order_supporteds" , "order_debats" })
	@ManyToOne()  
    @JoinColumn(name = "user_Creditor_Id")
    private User userCreditor; 
	
	@JsonIgnore
    @ManyToOne()  
    @JoinColumn(name = "user_debtor_Id")
    private User userdebtor;
	
	
	 @OneToMany(mappedBy = "supportorderId"  ,  cascade = CascadeType.ALL)
	 private List<Ordersupportofproduct> ordersupportofproduct ;
 
    @Column(name = "quantity")
    private int total_of_amount ;
 

}