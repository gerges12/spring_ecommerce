package com.luv2code.ecommerce.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.ecommerce.dao.ProductRepository;
import com.luv2code.ecommerce.dao.CommentRepository;
import com.luv2code.ecommerce.dao.ProductCategoryRepository;

import com.luv2code.ecommerce.dto.productRequest;
import com.luv2code.ecommerce.entity.Comment;
import com.luv2code.ecommerce.entity.OrderProduct;
import com.luv2code.ecommerce.entity.Product; 
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.exceptions.ProductNotFoundException;
import com.luv2code.ecommerce.exceptions.productCategoryNotFoundException;
import com.luv2code.ecommerce.exceptions.ProductAlreadyExistException;

@Service
public class productService {
	
	@Autowired
	ProductRepository productRepository  ;
	
	@Autowired
	ProductCategoryRepository  Productcategoryrepository  ;
	
	
	
	@Autowired
	AuthService authService ;
	
	@Autowired
	CommentRepository commentRepository ;
	
	public List<Product> getallproduct(){
		
		return productRepository.findAll()  ;
	}
	
	
	public void deleteByiId(Long orderId) {
	if( ! productRepository.existsById(orderId)) {
		throw new ProductNotFoundException("product id not found " + orderId.toString() )  ;
	}	
		productRepository.deleteById(orderId)  ;

	}


	public Product findById(Long orderId) {
		
				
		Product product = productRepository.findById(orderId)
                .orElseThrow(  () -> new ProductNotFoundException("product id not found " + orderId.toString()) ) ;
         
	   int v = product.getViews();

		product.setViews(v+1);
		
		productRepository.save(product)  ; 

		return product  ;
		
		
		}


	public Product save(productRequest productrequest) {
		Product product = new Product()  ;
		
		ProductCategory  productcategory =	Productcategoryrepository.findByCategoryName(productrequest.getCategoryName())	
		.orElseThrow(() -> new productCategoryNotFoundException("this category not found " +productrequest.getCategoryName()))  ;
		
		if (productRepository.findByName(productrequest.getName()).isPresent() ) 
		{
		 throw new ProductAlreadyExistException("product " +productrequest.getName() + " already saved ") ;
		}
		
		product.setName(productrequest.getName());
		product.setUnitPrice(productrequest.getUnitPrice());
		product.setActive(true);
		product.setSku(productrequest.getSku());
		product.setImageUrl("assets/images/products/books/book-luv2code-1006.png");
		product.setCategory(productcategory)  ;
        product.setLikes(0)  ;
        product.setDislikes(0)  ;

		product.setViews(1) ;
		product.setDescription(productrequest.getDescription())  ;
		product.setUnitsInStock(productrequest.getUnitsInStock())  ;

		product.setUser(authService.getCurrentUser()) ;  
		
		
		  product = productRepository.save(product)  ;


		return product ;
	}


	public List<Product> recommendedProduct() {
		// TODO Auto-generated method stub
		return productRepository.findRecommendedProduct()  ;
	}


	public List<Product> getcommentedProduct() {

		User user = authService.getCurrentUser()  ;
		List<Product> products =  new ArrayList<Product> ()  ;

		List<Comment> comments = commentRepository.findByUser(user)  ;
		
	
		for (Comment comment :comments) {
        	
			Product product = comment.getProduct()  ;
			System.out.println(product);
			
		    products.add(product)  ;
			
		}
		
  		return products ;     
		
	}


	public List<Product> getProductBySearch(String search) {
		
		return  productRepository.findByNameContaining(search )  ;
	}


	public List<Product> searchbycategory(String categoryName) {
		
		ProductCategory  productcategory =	Productcategoryrepository.findByCategoryName(categoryName)	
				.orElseThrow(() -> new productCategoryNotFoundException("this category not found "))  ;
		
		// TODO Auto-generated method stub
		return  productRepository.findByCategory(productcategory )  ;
	}


	public Product updateProduct(productRequest productrequest) {
		
		Product product  = productRepository.findById(productrequest.getId()) 
		.orElseThrow(() -> new ProductNotFoundException("this product not found ")) ;
		
		
		product.setName(productrequest.getName());
		product.setUnitPrice(productrequest.getUnitPrice());
		product.setDescription(productrequest.getDescription())  ;
		product.setUnitsInStock(productrequest.getUnitsInStock())  ;

		// TODO Auto-generated method stub
		return productRepository.save(product)  ;
	}

}
