package com.luv2code.ecommerce.entity;

import lombok.Data;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="product")
@Data
public class Product     {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long product_id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    @Column(name = "sku")
    private String sku;

   
    
    
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price")
    private int unitPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "active")
    private boolean active;

    @Column(name = "units_in_stock")
    private int unitsInStock;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;
    
    @Column(name = "views" , nullable = true)
    private  Integer   views ;
    
    @Column(name = "likes" , nullable = true)
    private  Integer   likes ;
    
    @Column(name = "dislikes" , nullable = true)
    private  Integer   dislikes ;
    
	@JsonIgnore
    @OneToMany(mappedBy = "product")
	 private List<OrderProduct> orderProduct ;  
    
	
	@JsonIgnore
    @OneToMany(mappedBy = "product")
	 private List<Ordersupportofproduct> ordersupportofproduct ;
    
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "userId")
    private User user;
    
    @JsonIgnore
    @OneToMany(mappedBy = "product")
	 private List<Vote> votes = new ArrayList<Vote>(); 
    
    
     @JsonIgnoreProperties({"comment_id"  ,"product" ,"user" })
     @OneToMany(mappedBy = "product")
	 private List<Comment> comments = new ArrayList<Comment>(); 
   
    
   
    public String toString(){//overriding the toString() method  
		  return "the product of " + name + "which " +  user.getUsername() +" was entered is having  "+views+" views" +" with price  "+ unitPrice ;
		 } 
    
    
    
    
    
    
    
    
    
    
}
