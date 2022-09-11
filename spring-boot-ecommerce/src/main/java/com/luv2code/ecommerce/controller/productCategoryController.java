package com.luv2code.ecommerce.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.service.productCategoryService;
import com.luv2code.ecommerce.service.productService;

@RestController
@RequestMapping("productCategory")
public class productCategoryController {
	
	
	@Autowired
	productCategoryService  productcattegoryservice  ;
	
    @GetMapping("/all")
    public List<ProductCategory>  getall() {
    	return productcattegoryservice.getallproduct() ;
    }

}
