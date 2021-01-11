package com.infy.ekart.entity;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EK_DEALS_FOR_TODAY")
public class DealsForToday {
	
	@Id
	@Column(name="DEAL_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer dealId;
	
	@Column(name="PRODUCT_ID")
	private Integer productId;
	
	@Column(name="DEAL_DISCOUNT")
	private Double dealDiscount;
	
	@Column(name="DEAL_STARTS_AT")
	private LocalDateTime dealStartsAt;
	
	@Column(name="DEAL_ENDS_AT")
	private LocalDateTime dealEndsAt;
	
	

	@Column(name="SELLER_EMAIL_ID")
	private String sellerEmailId;
	
	

	public Integer getDealId() {
		return dealId;
	}

	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getDealDiscount() {
		return dealDiscount;
	}

	public void setDealDiscount(Double dealDiscount) {
		this.dealDiscount = dealDiscount;
	}

	public LocalDateTime getDealStartsAt() {
		return dealStartsAt;
	}

	public void setDealStartsAt(LocalDateTime dealStartsAt) {
		this.dealStartsAt = dealStartsAt;
	}

	public LocalDateTime getDealEndsAt() {
		return dealEndsAt;
	}

	public void setDealEndsAt(LocalDateTime dealEndsAt) {
		this.dealEndsAt = dealEndsAt;
	}

	public String getSellerEmailId() {
		return sellerEmailId;
	}

	public void setSellerEmailId(String sellerEmailId) {
		this.sellerEmailId = sellerEmailId;
	}
}
