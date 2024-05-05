package com.access.productInventoryTracker.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.access.productInventoryTracker.model.Product;
import com.access.productInventoryTracker.repository.custom.CustomRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CustomRepositoryImpl implements CustomRepository {
	@PersistenceContext
	private EntityManager em;


	@Override
	public Optional<List<Product>> searchProductByDB(Double priceFrom, Double priceTo, String category, Boolean availablity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> query = cb.createQuery(Product.class);
		Root<Product> queryRoot = query.from(Product.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if (priceFrom != null) {
			predicates.add(cb.and(cb.greaterThanOrEqualTo(queryRoot.get("price"), priceFrom)));
		}
		if (priceTo != null) {
			predicates.add(cb.and(cb.lessThanOrEqualTo(queryRoot.get("price"), priceTo)));
		}

		if (category != null) {
			predicates.add(cb.and(cb.equal(queryRoot.get("category"), category)));
		}
		if (availablity != null) {
			predicates.add(cb.and(cb.equal(queryRoot.get("available"), availablity)));
		}
		query.select(queryRoot);
		if(predicates.size() > 0) {
            query.where(predicates.toArray(new Predicate[]{}));
        }

		List<Product> productList = em.createQuery(query).getResultList();
		Optional<List<Product>> products = Optional.of(productList);
		
		return products;
	}
}
