package com.luv2code.ecommerce.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.dto.productCategoryRequest;
import com.luv2code.ecommerce.dto.productRequest;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.service.productCategoryService;
import com.luv2code.ecommerce.service.productService;

@RestController
@RequestMapping("productCategory")
public class productCategoryController {
	
	
	@Autowired
	productCategoryService  productcategoryservice  ;
	
    @GetMapping("/all")
    public List<ProductCategory>  getall() {
    	return productcategoryservice.getallproductcategory() ;
    }
    
    
    @PostMapping("/createProductCategory")
    public ProductCategory createProduct (@RequestBody productCategoryRequest productCategoryrequest) {
    	
    	return productcategoryservice.save(productCategoryrequest)  ;
    	
    	
    }

}
