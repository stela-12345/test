package com.infy.ekart.api;

import java.time.LocalDateTime;

public class ProductDetails {
	
	Integer productId;
	String sellerEmailId;
	Double discount;
	LocalDateTime startDateTime;
	LocalDateTime endDateTime;
	public Integer getProductId() {
		return productId;
	}
	public String getSellerEmailId() {
		return sellerEmailId;
	}
	public Double getDiscount() {
		return discount;
	}
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	
}
