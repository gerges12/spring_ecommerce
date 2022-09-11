package com.luv2code.ecommerce.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.luv2code.ecommerce.dto.productRequest;
import com.luv2code.ecommerce.entity.Comment;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.service.productService;

@RestController
@RequestMapping("product")
public class productController {

	@Autowired
	productService  productservice  ;
	
	
	
       @GetMapping("/search")
       @ResponseBody
       public List<Product> search(@RequestParam String search) {
	       
	        return productservice.getProductBySearch(search) ;
	    }
       
       
       @GetMapping("/searchByCategory")
       public List<Product>  searchbycategory(@RequestParam String CategoryName) {
       	return productservice.searchbycategory(CategoryName) ;
       }
	
	
	
    @GetMapping("/all")
    public List<Product>  getall() {
    	return productservice.getallproduct() ;
    }

    @DeleteMapping("/delete/{id}")
    public void deletebyid(@PathVariable("id") Long orderId) {
    	 productservice.deleteByiId(orderId) ;
    	 
    }
    
    @GetMapping("/getproduct/{id}")
    public Product  getProductbyid(@PathVariable("id") Long orderId) {
    	return  productservice.findById(orderId) ;
    	 
    }
    
    @PostMapping("/createproduct")
    public Product createProduct (@RequestBody productRequest product) {
    	
    	return productservice.save(product)  ;
    	
    	
    }
    
    
    @GetMapping("/recommendedProduct")
    public List<Product>  getrecommendedProduct() {
    	return productservice.recommendedProduct() ;
    }
    
    @GetMapping("/productyouCommented")
    public List<Product>  commentedProduct() {
    	return productservice.getcommentedProduct() ;
    }
    
    
    
    

}