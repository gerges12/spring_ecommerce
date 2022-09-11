package com.luv2code.ecommerce.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.ecommerce.dao.ProductCategoryRepository;
import com.luv2code.ecommerce.dao.ProductRepository;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;

@Service
public class productCategoryService {
	
	
	@Autowired
	ProductCategoryRepository productcattegoryrepository  ;
	
	
	public List<ProductCategory> getallproduct(){
		
		
		return productcattegoryrepository.findAll()  ;
		 
	}

}
