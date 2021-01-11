package com.infy.ekart.service;

import java.time.LocalDateTime;
import java.util.List;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.dto.ProductDTO;
import com.infy.ekart.exception.EKartException;

public interface DftSellerProductService {

	public Integer removeProductDeals(DealsForTodayDTO dealDTO) throws EKartException;
	
	public List<ProductDTO> getProductListNotInDeal(String sellerEmailId) throws EKartException;

	public Integer addProductInDeal(Integer productId, String sellerEmailId, Double discount, LocalDateTime startDateTime, LocalDateTime endDateTime) throws EKartException;
	
	public List<DealsForTodayDTO> getProductInDealList(String sellerEmailId) throws EKartException;
	
}
