package com.infy.ekart.api;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.dto.ProductDTO;
import com.infy.ekart.entity.DealsForToday;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.service.DftSellerProductService;


@CrossOrigin 
@RestController
@RequestMapping(value = "SellerDealsForTodayAPI")
public class SellerDealsForTodayAPI {
	
	@Autowired
	private DftSellerProductService dftSellerProductService;
	
	@Autowired
	private Environment environment;
	
	static Log Logger = LogFactory.getLog(SellerDealsForTodayAPI.class);
	 
	
    //removing the products from deals
	//for user story 03 
	
	@PostMapping(value = "removeProductInDeals")
	public ResponseEntity<String> removeProductInDeals(@RequestBody DealsForTodayDTO dealsForTodayList)throws Exception{
		try
		{
			Logger.info("REMOVING PRODUCT DETAILS, PRODUCT ID: "+dealsForTodayList.getProductId());
			Integer deleteProductFromDeals = dftSellerProductService.removeProductDeals(dealsForTodayList);
			Logger.info("PRODUCT DETAILS REMOVED SUCCESSFULLY, PRODUCT ID: "+dealsForTodayList.getProductId());
			String successMessage ="Product deleted successfully with product id: "+deleteProductFromDeals;
			return new ResponseEntity<String>(successMessage,HttpStatus.OK);
				}
		catch (Exception exception){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(exception.getMessage()));
		}
	}
	 
	 
	 // displaying the products on web-page  when deal starts  
	//after connecting to backend 
	
	@PostMapping(value = "addProductInDeal")
	public ResponseEntity<String> addProductInDeal(@RequestBody ProductDetails productDetailsOfDeals) throws EKartException {
		try {
			Logger.info("ADDING PRODUCT TO DEALS FOR TODAY, PRODUCT ID: "+productDetailsOfDeals.getProductId()+"\t SELLER NAME ="+productDetailsOfDeals.getSellerEmailId());
			Integer productId = dftSellerProductService.addProductInDeal(productDetailsOfDeals.getProductId(),productDetailsOfDeals.getSellerEmailId(),productDetailsOfDeals.getDiscount(),productDetailsOfDeals.getStartDateTime(), productDetailsOfDeals.getEndDateTime());
			Logger.info("PRODUCT ADDED SUCCESSFULLY TO THE DEALS FOR TODAY, PRODUCT ID: "+productDetailsOfDeals.getProductId()+"\t SELLER NAME ="+productId);
			String successMessage= environment.getProperty("SellerProductAPI.PRODUCT_ADDED_SUCCESSFULLY")+productDetailsOfDeals.productId;
			return new ResponseEntity<String>(successMessage, HttpStatus.OK);
		}
		catch(EKartException e) 
		{
			if(e.getMessage().contains("Validator")) 
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
		
	}
	
	@PostMapping(value = "productsCurrentlyBeingSold")
	public ResponseEntity<List<ProductDTO>> productsCurrentlyBeingSold(@RequestBody String sellerEmailId) throws EKartException {
		try {
			List<ProductDTO> productsNotInDeal = dftSellerProductService.getProductListNotInDeal(sellerEmailId);
			return new ResponseEntity<List<ProductDTO>>(productsNotInDeal, HttpStatus.OK);
		}
		catch (EKartException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}

	
	 
	// finding products in for deals 
	
	@PostMapping(value = "productsInDealList")
	public ResponseEntity<List<DealsForTodayDTO>> productsInDealList(@RequestBody String sellerEmailId) throws EKartException {
		try {
			List<DealsForTodayDTO> productsInDeal = dftSellerProductService.getProductInDealList(sellerEmailId);
			return new ResponseEntity<List<DealsForTodayDTO>>(productsInDeal, HttpStatus.OK);
		}
		catch (EKartException exception) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(exception.getMessage()));
		}
	}
	
}
