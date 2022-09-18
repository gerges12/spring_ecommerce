package com.luv2code.ecommerce.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.persistence.*;


import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user")
@Data
public class User {
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    
    @Email
    @NotEmpty(message = "Email is required")
    private String email;
    private Date created;

    private boolean enabled;
    
    private boolean is_sponser ;
    
    private int total_of_orders ;
    
    @OneToMany(mappedBy = "userCreditor")
	 private List<SupportOrder> order_supporteds ;

  //  private SupportOrder order_supported;  
    
    @OneToMany(mappedBy = "userdebtor")
	 private List<SupportOrder> order_debats ;

    //private SupportOrder order_debat;  
    
  
    
    
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
	 private List<Comment> comments = new ArrayList<Comment>();
    
    
    
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    private List<Vote> votes = new ArrayList<Vote>();
    
    
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<Product>();
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}
