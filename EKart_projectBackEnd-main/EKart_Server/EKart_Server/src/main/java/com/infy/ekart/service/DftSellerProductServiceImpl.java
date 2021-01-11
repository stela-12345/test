package com.infy.ekart.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.dto.ProductDTO;
import com.infy.ekart.entity.DealsForToday;
import com.infy.ekart.entity.Product;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.DealsForTodayRepository;
import com.infy.ekart.validator.DealsForTodayValidator;

@Service(value = "dftSellerProductService")
@Transactional
public class DftSellerProductServiceImpl implements DftSellerProductService{
	
	@Autowired
	private DealsForTodayRepository dealsForTodayRepository; 
	 
	//for user story 03 
	//to remove product from deals :modified
		
	@Override
	public Integer removeProductDeals(DealsForTodayDTO dealsForToday) throws EKartException{
		Optional<DealsForToday> optional = dealsForTodayRepository.findById(dealsForToday.getDealId());
		DealsForToday dealForToday = optional.orElseThrow(()-> new EKartException("DftSellerProductService.NO_DEAL_FOUND"));
		Integer productId =dealForToday.getProductId();
		dealsForTodayRepository.delete(dealForToday);
		return productId;
	} 
	 
	//for user story 02 get product which are not under deals offers
	
	@Override
	public List<ProductDTO> getProductListNotInDeal(String sellerEmailId) throws EKartException{
		List<Product> products = dealsForTodayRepository.findBySellersEmailId(sellerEmailId);
		List<ProductDTO> productsNotInsideDealsList = new ArrayList<ProductDTO>();
		
		for(Product p : products) {
			ProductDTO productDTO = new ProductDTO();
			productDTO .setProductId(p.getProductId());
			productDTO .setName(p.getName());
			productDTO .setDescription(p.getDescription());
			productDTO .setBrand(p.getBrand());
			productDTO .setCategory(p.getCategory());
			productDTO .setPrice(p.getPrice());
			productDTO .setDiscount(p.getDiscount());
			productDTO .setQuantity(p.getQuantity());
			productDTO .setErrorMessage(null);
			productDTO .setSuccessMessage(null);
			productDTO .setSellerEmailId(p.getSellerEmailId());
			
			productsNotInsideDealsList.add(productDTO );
		}
		return productsNotInsideDealsList;
	}

	@Override
	public Integer addProductInDeal(Integer productId, String sellerEmailId, Double discount, LocalDateTime startDateTime, LocalDateTime endDateTime) throws EKartException{
		DealsForTodayValidator.validateProductForDeal(startDateTime, endDateTime);
		DealsForToday dealForToday = new DealsForToday();
		dealForToday .setProductId(productId);
		dealForToday .setSellerEmailId(sellerEmailId);
		dealForToday .setDealDiscount(discount);
		dealForToday .setDealStartsAt(startDateTime);
		dealForToday .setDealEndsAt(endDateTime);
		
		DealsForToday deals = dealsForTodayRepository.save(dealForToday );
		
		return deals.getDealId();
	}
	
	@Override
	public List<DealsForTodayDTO> getProductInDealList(String sellerEmailId) throws EKartException 
	{  
		//retrieving seller data from repository 
		List<DealsForToday> dftList = dealsForTodayRepository.findBySellerEmailId(sellerEmailId);
		if(dftList.isEmpty()) {
			 throw new EKartException("DealsForToday.SELLER_NOT_FOUND");
		}
		List<DealsForTodayDTO> productInDealList = new ArrayList<>();
	//	DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy");
	//	String today=LocalDateTime.now().format(format);

		for(DealsForToday dft: dftList) {
			//String startdate=dft.getDealStartsAt().format(format);
			
			DealsForTodayDTO dealForTodayDTO=new DealsForTodayDTO();
			
			 dealForTodayDTO.setDealId(dft.getDealId());
			 dealForTodayDTO.setProductId(dft.getProductId());
			 dealForTodayDTO.setDealDiscount(dft.getDealDiscount());
			 dealForTodayDTO.setDealStartsAt(dft.getDealStartsAt());
			 dealForTodayDTO.setDealEndsAt(dft.getDealEndsAt());
			 dealForTodayDTO.setSellerEmailId(dft.getSellerEmailId());
			
			
			productInDealList.add( dealForTodayDTO);
			
		}
		return productInDealList;
	}
	
	

}
