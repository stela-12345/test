package com.infy.ekart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	List<Product> findBySellerEmailId(String sellerEmailId);
}
