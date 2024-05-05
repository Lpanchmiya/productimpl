package com.access.productInventoryTracker.repository;

import com.access.productInventoryTracker.model.Product;
import com.access.productInventoryTracker.repository.custom.CustomRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,CustomRepository {
    // This interface method findAll() is provided by JpaRepository so no need to implement it.
    // You can add custom methods here if needed, for example: 
     List<Product> findByCategory(String category);

     List<Product> findByAvailable(boolean available);

	List<Product> findByPrice(double price);

	List<Product> findByName(String name);

	
	
}
