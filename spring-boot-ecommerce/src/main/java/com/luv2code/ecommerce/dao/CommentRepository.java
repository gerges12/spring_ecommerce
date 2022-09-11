package com.luv2code.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.ecommerce.entity.Comment;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	
    List<Comment> findByProduct(Product product);

	List<Comment> findByUser(User user);


}
