package com.luv2code.ecommerce.entity;

import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="order") 
@Data
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
	private Long order_id ;
	
	@Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;
	
	@JsonIgnore
	@ManyToOne(fetch = LAZY)  
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
	
	
	 
	 @OneToMany(mappedBy = "order"  ,  cascade = CascadeType.ALL)
	 private List<OrderProduct> orderProduct ;
	 
	 @Column(name = "price")
     private Integer price ;
	 
    
	/*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "order_product",
        joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
    private List<Product> products;  */

}
