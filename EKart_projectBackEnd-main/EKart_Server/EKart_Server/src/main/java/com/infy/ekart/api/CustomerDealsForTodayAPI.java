package com.infy.ekart.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.service.CustomerDealForTodayService;


@RestController
@CrossOrigin
@RequestMapping("CustomerDealsForTodayAPI")
public class CustomerDealsForTodayAPI {

	@Autowired
	private CustomerDealForTodayService customerDealForTodayService;

	@Autowired
	private Environment enviornment;

	@PostMapping(value="productInTodayDeal")
	public ResponseEntity<?> productInTodayDeal() throws EKartException{
		try {
			List<DealsForTodayDTO> productInTodayDeal=customerDealForTodayService.getCustomerDeals();
			return new ResponseEntity<List<DealsForTodayDTO>>(productInTodayDeal,HttpStatus.OK);
		}
		catch(Exception e) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, enviornment.getProperty(e.getMessage()));

		}

	}
}