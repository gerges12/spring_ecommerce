package com.luv2code.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.dto.orderRequest;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.OrderProduct;
import com.luv2code.ecommerce.service.orderService;

@RestController
@RequestMapping("order")
public class orderController {
	
	@Autowired
	orderService  orderservice  ;
	
	@PostMapping("/createorder")
    public Order createProduct (@RequestBody orderRequest  orderRequest) {
    	
    	return orderservice.save(orderRequest.getOrder())  ; 
    	
    	
    }
	
	@GetMapping("/yourorder")
    public List<Order> getyourorder () {
    	
    	return orderservice.orderofcurrenruser() ; 
    	
    	
    }
	
	@DeleteMapping("/deleteAllOrder")
    public void deleteAllOrder () {
    	
    	 orderservice.deleteAllOrder()  ; 
    	
    	
    }

}
