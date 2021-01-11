package com.infy.ekart.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.entity.DealsForToday;
import com.infy.ekart.entity.Product;

public interface DealsForTodayRepository extends CrudRepository<DealsForToday, Integer>{
	
	public List<DealsForToday> findBySellerEmailId(String sellerEmailId);
	
	public List<DealsForToday> findAll();
	
	@Query("select p from Product p where p.sellerEmailId=:sellerEmailId and p.productId NOT IN (select d.productId from DealsForToday d where d.sellerEmailId=:sellerEmailId)")
	public List<Product> findBySellersEmailId(String sellerEmailId);

	public List<DealsForToday> findByDealStartsAtAfterAndDealEndsAtBefore(LocalDateTime dealStartAt , LocalDateTime dealEndsAt);

	
	
}
