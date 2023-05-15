package com.radovan.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/mvc/products")
public class ProductController {

	// No product service here
	
	@GetMapping(value = "/allProducts")
	public String getAllProducts() {
		return "fragments/productList :: ajaxLoadedContent";
	}
	
	@GetMapping(value="/productDetails/{productId}")
	public String getProductDetails(@PathVariable ("productId") Integer productId,ModelMap map) {
		map.put("productId", productId);
		return "fragments/productDetails :: ajaxLoadedContent";
	}
	
	@GetMapping(value="/addProduct")
	public String renderProductForm() {
		return "fragments/productForm :: ajaxLoadedContent";
	}
	
	@GetMapping(value="/updateProduct/{productId}")
	public String renderUpdateProductForm(@PathVariable ("productId") Integer productId, ModelMap map) {
		map.put("productId", productId);
		return "fragments/productUpdateForm :: ajaxLoadedContent";
	}
	
	
	
	
	
	
}
