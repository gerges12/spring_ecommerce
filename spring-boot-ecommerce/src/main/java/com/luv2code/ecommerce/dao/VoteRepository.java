package com.luv2code.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote , Long> {
	
	
	Vote findByProductAndUser(Product product, User user) ;


}
