package com.luv2code.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.ecommerce.entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
	
	
	


}
