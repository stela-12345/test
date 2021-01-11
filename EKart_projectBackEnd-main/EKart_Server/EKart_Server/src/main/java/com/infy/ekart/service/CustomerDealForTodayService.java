package com.infy.ekart.service;

import java.util.List;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.exception.EKartException;

public interface CustomerDealForTodayService {
	public List <DealsForTodayDTO> getCustomerDeals() throws EKartException;
	public List<DealsForTodayDTO> Valid() throws EKartException;

}
