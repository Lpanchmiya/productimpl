package com.access.productInventoryTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.access.productInventoryTracker.dto.ProductDTO;
import com.access.productInventoryTracker.service.ProductService;

@RestController
@RequestMapping("/")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(path = "/list")
	public ResponseEntity<List<ProductDTO>> searchProduct(
			@RequestParam(name = "priceFrom", required = false) Double priceFrom,
			@RequestParam(name = "priceTo", required = false) Double priceTo,
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "availability", required = false) Boolean availability) {
		
		List<ProductDTO> products = productService.searchProductByDB(priceFrom, priceTo, category, availability);
		
		return ResponseEntity.ok(products);
	}

	
	@PostMapping(path = "/create")
	public ResponseEntity<List<ProductDTO>> createProduct() {
		
		productService.createProduct();
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	
	
}
