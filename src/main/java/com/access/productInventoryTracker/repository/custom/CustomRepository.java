package com.access.productInventoryTracker.repository.custom;

import java.util.List;
import java.util.Optional;

import com.access.productInventoryTracker.model.Product;

public interface CustomRepository {
	Optional<List<Product>> searchProductByDB(Double priceFrom, Double priceTo, String category, Boolean availablity);

}
