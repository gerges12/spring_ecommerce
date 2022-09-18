package com.luv2code.ecommerce.dao;


import com.luv2code.ecommerce.entity.Comment;
import com.luv2code.ecommerce.entity.SupportOrder;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportOrderRepository extends JpaRepository<SupportOrder, Long> {
	

    List<SupportOrder>  findByuserdebtor(User user)  ;

	List<SupportOrder> findByuserCreditor(User user)  ;  


}
