package com.luv2code.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.ecommerce.dao.OrderProductRepository;
import com.luv2code.ecommerce.dao.OrderRepository;
import com.luv2code.ecommerce.dao.ProductRepository;
import com.luv2code.ecommerce.dto.orderRequest;
import com.luv2code.ecommerce.dto.orequest;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.OrderProduct;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.exceptions.ProductNotFoundException;

@Service
public class orderService {
	
	@Autowired
    OrderRepository orderRepository  ;
	
	@Autowired
	OrderProductRepository    orderProductRepository ;
	
	@Autowired
	ProductRepository  productrepository  ;
	
	@Autowired
	AuthService authService ;

	public Order save(ArrayList<orequest> orderRequest) {
		
		
		Order order =  new Order()  ;
		
		  int price = 0 ;
		
		 ArrayList < OrderProduct > allorder = new ArrayList < OrderProduct > ();

        for (orequest i : orderRequest) {
        	 
        	OrderProduct Orderproduct = new OrderProduct() ;

        	Product product = productrepository.findById(i.getProduct_id())
        		         .orElseThrow(  
      () -> new ProductNotFoundException("product id not found " + i.getProduct_id()) ) ;
        	
        	if (product.getUnitsInStock()==0) {
        		throw new ProductNotFoundException("stock have not any " + product.getName() + "s" ) ;
        	}
        	
        	Orderproduct.setQuantity(i.getQuantity());


        	Orderproduct.setProduct(product); 
        	

          
        	allorder.add(Orderproduct)  ;
        	price +=  i.getQuantity()*product.getUnitPrice()  ;
        	
        }
            order.setUser(authService.getCurrentUser());
		order.setOrderProduct(allorder);
		order.setPrice(price)   ;
	
	
	
		order = orderRepository.save(order)  ;
		for (OrderProduct o :allorder) {
        	o.setOrder(order);
        	
        	orderProductRepository.save(o) ;

		}
		
		return order ;

		
		
	
	//	System.out.println("free    " +   order.getOrderProduct()  )   ;
		
	}



	public List<Order> orderofcurrenruser() {
		
		User user = authService.getCurrentUser()  ;

		List<Order> order =  new ArrayList<Order>()  ;

		
		
		 order = orderRepository.findByUser(user)  ;

		 
		 return order ;
	}



	public void deleteAllOrder() {
		// TODO Auto-generated method stub
		orderRepository.deleteAll();;
	}

}
