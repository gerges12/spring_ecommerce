package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Comment;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
    
    List<Product> findByNameContaining( String name );

	
    Optional<Product> findByName(String productname);
    
    
    List<Product>  findByCategory(ProductCategory productCategory)  ;
    
    
    
    @Query("SELECT u FROM Product u WHERE u.views >= 5")
    List<Product> findRecommendedProduct();

	//List<Product> findCommentedProduct(User user);

	//Product findByComments(Comment comment);

    
  


}
