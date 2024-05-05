package com.access.productInventoryTracker.service;

import com.access.productInventoryTracker.dto.ProductDTO;
import com.access.productInventoryTracker.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.access.productInventoryTracker.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductDTO> searchProductByStreams(double priceFrom, double priceTo, String category, boolean availablity) {
		
		List<Product> products = productRepository.findAll();
		
		List<Product> filteredProducts = products.stream()
        .filter(product ->
                (priceFrom <= 0 || product.getPrice() >= priceFrom) &&
                (priceTo <= 0 || product.getPrice() <= priceTo) &&
                (category == null || product.getCategory().equals(category)) &&
                (!availablity || product.isAvailable()))
        .collect(Collectors.toList());
		
		
		List<ProductDTO> productDTOList = null;
		if (filteredProducts != null) {
			productDTOList = filteredProducts.stream().map(s -> mapDTO(s)).collect(Collectors.toList());
		}
		
		return productDTOList;
	}
	
	
	public List<ProductDTO> searchProductByDB(Double priceFrom, Double priceTo, String category, Boolean availablity) {
		
		List<Product> products = productRepository.searchProductByDB(priceFrom, priceTo, category, availablity)
				.orElseThrow(()-> new RuntimeException("No product found with given criteria."));
		
		List<ProductDTO> productDTOList = null;
		if (products != null) {
			productDTOList = products.stream().map(s -> mapDTO(s)).collect(Collectors.toList());
		}
		
		return productDTOList;
	}
	
	
	
	private ProductDTO mapDTO(Product product) {
		ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getPrice(),
				product.getCategory(), product.isAvailable());
		return productDTO;
	}

	public void createProduct() {
		
		List<Product> products = Arrays.asList(
	            new Product(1L, "Laptop", 1500.0, "Electronics", true),
	            new Product(2L, "Smartphone", 800.0, "Electronics", false),
	            new Product(3L, "Coffee Maker", 100.0, "Home Appliances", true),
	            new Product(4L, "Blender", 150.0, "Home Appliances", true),
	            new Product(5L, "T-Shirt", 30.0, "Apparel", true),
	            new Product(6L, "Jeans", 45.0, "Apparel", true),
	            new Product(7L, "Desk Lamp", 89.99, "Home Appliances", false),
	            new Product(8L, "Wall Art", 120.0, "Home Decor", true),
	            new Product(9L, "Sneakers", 75.0, "Apparel", true),
	            new Product(10L, "Wristwatch", 250.0, "Accessories", false),
	            new Product(11L, "Backpack", 60.0, "Accessories", true),
	            new Product(12L, "Microwave Oven", 99.0, "Home Appliances", false),
	            new Product(13L, "Floor Rug", 150.0, "Home Decor", true),
	            new Product(14L, "Speaker", 300.0, "Electronics", true),
	            new Product(15L, "E-reader", 200.0, "Electronics", false),
	            new Product(16L, "Gaming Console", 499.99, "Electronics", true),
	            new Product(17L, "Office Chair", 220.0, "Office Supplies", true),
	            new Product(18L, "Pen Set", 29.99, "Office Supplies", true),
	            new Product(19L, "Mountain Bike", 489.0, "Outdoor", true),
	            new Product(20L, "Camping Tent", 270.0, "Outdoor", false)
	        );
		
		productRepository.saveAll(products);
		
	}
}
