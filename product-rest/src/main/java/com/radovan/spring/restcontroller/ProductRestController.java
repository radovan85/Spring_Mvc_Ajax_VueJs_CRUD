package com.radovan.spring.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radovan.spring.dto.ProductDto;
import com.radovan.spring.exceptions.DataNotValidatedException;
import com.radovan.spring.service.ProductService;

@RestController
@RequestMapping(value = "/api/products")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/allProducts")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> allProducts = productService.listAll();
		return ResponseEntity.ok().body(allProducts);
	}

	@GetMapping(value = "/productDetails/{productId}")
	public ResponseEntity<ProductDto> getProductDetails(@PathVariable("productId") Integer productId) {
		ProductDto product = productService.getProductById(productId);
		return ResponseEntity.ok().body(product);
	}

	@PostMapping(value = "/storeProduct")
	public ResponseEntity<String> storeProduct(@Validated @RequestBody ProductDto product, Errors errors) {
		if (errors.hasErrors()) {
			Error error = new Error("Data is not validated!"); // java.lang.Error
			throw new DataNotValidatedException(error);
		}

		ProductDto storedProduct = productService.addProduct(product);
		return ResponseEntity.ok().body("Product with id " + storedProduct.getProductId() + " is stored to database");
	}

	@PutMapping(value = "/updateProduct/{productId}")
	public ResponseEntity<String> updateProduct(@Validated @RequestBody ProductDto product,
			@PathVariable("productId") Integer productId, Errors errors) {

		if (errors.hasErrors()) {
			Error error = new Error("Data is not validated!"); // java.lang.Error
			throw new DataNotValidatedException(error);
		}

		ProductDto updatedProduct = productService.updateProduct(productId, product);
		return ResponseEntity.ok().body("Product with id " + updatedProduct.getProductId() + " is updated!");
	}

	@DeleteMapping(value = "/deleteProduct/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") Integer productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.ok("Product with id " + productId + " is permanently deleted!");
	}

}
