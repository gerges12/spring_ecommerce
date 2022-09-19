package com.luv2code.ecommerce.dao;


import com.luv2code.ecommerce.entity.Comment;
import com.luv2code.ecommerce.entity.SupportOrder;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.entity.Statusofrequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportOrderRepository extends JpaRepository<SupportOrder, Long> {
	

    List<SupportOrder>  findByuserdebtor(User user)  ;
    
    List<SupportOrder>  findByuserdebtorAndStatusofrequest(User user  , Statusofrequest statusofrequest  )  ;


    @Query("SELECT u FROM SupportOrder u WHERE u.statusofrequest = com.luv2code.ecommerce.entity.Statusofrequest.PENDING")
	List<SupportOrder> findByuserCreditor(User user)  ;  
    
    



}
