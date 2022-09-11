package com.luv2code.ecommerce.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class OrderProductId  implements Serializable {
 
    @Column(name = "order_id")
    private Long orderId;
 
    @Column(name = "product_id")
    private Long productId;
    
    
   
    
    
}
 
   