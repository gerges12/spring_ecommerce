package com.luv2code.ecommerce.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.ecommerce.dao.ProductCategoryRepository;
import com.luv2code.ecommerce.dao.ProductRepository;
import com.luv2code.ecommerce.dto.productCategoryRequest;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;

@Service
public class productCategoryService {
	
	
	@Autowired
	ProductCategoryRepository productcategoryrepository  ;
	
	
	public List<ProductCategory> getallproductcategory(){
		
		
		return productcategoryrepository.findAll()  ;
		 
	}


	public ProductCategory save(productCategoryRequest productCategoryrequest) {
		
		ProductCategory  productcategory  =  new ProductCategory()  ;
		productcategory.setCategoryName(productCategoryrequest.getCategoryName());
		return productcategoryrepository.save(productcategory)  ;
		
	}

}
