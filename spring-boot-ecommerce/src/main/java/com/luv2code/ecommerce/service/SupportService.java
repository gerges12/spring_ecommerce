package com.luv2code.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.ecommerce.dao.OrderProductRepository;
import com.luv2code.ecommerce.dao.OrderRepository;
import com.luv2code.ecommerce.dao.OrdersupportofproductRepository;
import com.luv2code.ecommerce.dao.ProductCategoryRepository;
import com.luv2code.ecommerce.dao.ProductRepository;
import com.luv2code.ecommerce.dao.SupportOrderRepository;
import com.luv2code.ecommerce.dao.UserRepository;
import com.luv2code.ecommerce.dto.FinancialSupportRequestdto;
import com.luv2code.ecommerce.dto.orequest;
import com.luv2code.ecommerce.dto.reply_for_support_request;
import com.luv2code.ecommerce.entity.OrderProduct;
import com.luv2code.ecommerce.entity.Ordersupportofproduct;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.entity.SupportOrder;
import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.entity.Statusofrequest;
import com.luv2code.ecommerce.exceptions.PermissionException;
import com.luv2code.ecommerce.exceptions.ProductNotFoundException;

@Service
public class SupportService {
	

	
	
	
	@Autowired
	OrdersupportofproductRepository   ordersupportofproductRepository ;
	
	@Autowired
	SupportOrderRepository  supportOrderRepository  ;
	
	@Autowired
    UserRepository  userRepository  ;
	
	@Autowired
	ProductRepository productrepository  ;
	

	
	
	
	@Autowired
	AuthService authService ;

	public String statusmessage;


	public String statusforreply;

	public SupportOrder save(FinancialSupportRequestdto supportRequestdto) {

		SupportOrder supportOrder  = new SupportOrder()  ;
		User user_Creditor = userRepository.findById(supportRequestdto.getUser_id())
		         .orElseThrow(  
        () -> new ProductNotFoundException("user not found") ) ;
		
		     if ( !user_Creditor.is_sponser() ) {
	           
	             throw new PermissionException("this user is not sponser")  ;
	
           }
		     
		     else if ( user_Creditor == authService.getCurrentUser()  ) {
		           
	             throw new PermissionException(" you can not send request to yourself")  ;
	
           }
	

		
		  int total_of_amount = 0 ;
			 ArrayList < Ordersupportofproduct > allOrdersupportofproduct = new ArrayList < Ordersupportofproduct > ();

			 for (orequest i : supportRequestdto.getOrderrequest()) {
				 Ordersupportofproduct ordersupportofproduct = new Ordersupportofproduct() ;

		        	Product product = productrepository.findById(i.getProduct_id())
		        		         .orElseThrow(  
		      () -> new ProductNotFoundException("product id not found " + i.getProduct_id()) ) ;
		        	
		        	
		        	
		        	ordersupportofproduct.setQuantity(i.getQuantity());


		        	ordersupportofproduct.setProduct(product); 
		        	

		          
		        	allOrdersupportofproduct.add(ordersupportofproduct)  ;
		        	total_of_amount +=  i.getQuantity()*product.getUnitPrice()  ;
		        	
		        }
		  
			 supportOrder.setUserdebtor(authService.getCurrentUser());
			 supportOrder.setUserCreditor(user_Creditor)  ;
			 supportOrder.setStatusofrequest(Statusofrequest.PENDING);
			 
			 supportOrder.setTotal_of_amount(total_of_amount);
			 supportOrder.setOrdersupportofproduct(allOrdersupportofproduct);
		  
			 supportOrder = supportOrderRepository.save(supportOrder)  ;
			 
			 for (Ordersupportofproduct o :allOrdersupportofproduct) {
		        	o.setSupportorderId(supportOrder);
		        	
		        	ordersupportofproductRepository.save(o) ;

				}
			 
			 
			 
		return supportOrder;

		
		
		
	}

	public void deleteSr(Long supportRequestId) {
		
		
		
		SupportOrder supportOrder = supportOrderRepository.findById(supportRequestId).orElseThrow(  
			      () -> new ProductNotFoundException("supportOrder not found ")) ;
		
		if (supportOrder.getUserdebtor() == authService.getCurrentUser() )
		{
			
			supportOrderRepository.delete(supportOrder);
			statusmessage =  "supportOrder deleted succesfully"  ;

		}
		

		
		else if (supportOrder.getUserdebtor() != authService.getCurrentUser() )
		{
			statusmessage =  ""  ;

			throw new  ProductNotFoundException(" you are not the user who have this supportOrder")  ;

		}
		
	}

	public List<SupportOrder> support_requests() {
		List<SupportOrder> SupportOrders  = supportOrderRepository.findByuserdebtor(authService.getCurrentUser())  ;
		return SupportOrders;
	}

	public List<SupportOrder> requests_for_reply() {
		List<SupportOrder> SupportOrders  = supportOrderRepository.findByuserCreditor(authService.getCurrentUser())  ;
		return SupportOrders;
	}

	public void reply_for_support_request(reply_for_support_request   reply) {
		
		SupportOrder supportOrder =	supportOrderRepository.findById(reply.getSupport_order_id()).orElseThrow(  
			      () -> new ProductNotFoundException("supportOrder not found ")) ;
		 ;
		 
		 if (supportOrder.getUserCreditor() != authService.getCurrentUser())
		 {
			throw new  ProductNotFoundException(" you are not the sponser for this support order")  ;
 
		 }
		
		 supportOrder.setStatusofrequest(reply.getStatus_of_reply());
		 statusforreply="you " + supportOrder.getStatusofrequest() + " successfully"  ;

		 
		 supportOrder = supportOrderRepository.save(supportOrder)  ;
		 
	}

	public List<SupportOrder> status_of_support_request(Statusofrequest statusofrequest) {

		List<SupportOrder> SupportOrders  = supportOrderRepository.findByuserdebtorAndStatusofrequest(authService.getCurrentUser() , statusofrequest )  ;
		return SupportOrders;
		
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
