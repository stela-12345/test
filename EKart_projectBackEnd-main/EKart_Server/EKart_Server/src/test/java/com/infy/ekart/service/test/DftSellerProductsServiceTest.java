package com.infy.ekart.service.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.dto.ProductDTO;
import com.infy.ekart.entity.DealsForToday;
import com.infy.ekart.entity.Product;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.DealsForTodayRepository;
import com.infy.ekart.service.DftSellerProductService;
import com.infy.ekart.service.DftSellerProductServiceImpl;

@SpringBootTest
public class DftSellerProductsServiceTest {
	
	@Mock
	private DealsForTodayRepository dealsForTodayRepository;
	
	@InjectMocks
	private DftSellerProductService dftSellerProductService=new DftSellerProductServiceImpl();
	
	@Test
	public void addProductInDealValid() throws EKartException{
		
		LocalDateTime dealStartOn=LocalDateTime.parse("2020-12-12T18:00");
		LocalDateTime dealEndsOn=LocalDateTime.parse("2020-12-12T20:00");
		
		DealsForToday deal=new DealsForToday();
		deal.setDealId(101);
		deal.setProductId(1001);
		deal.setSellerEmailId("xyz@infosys.com");
		deal.setDealDiscount(5.0);
		deal.setDealStartsAt(dealStartOn);
		deal.setDealEndsAt(dealEndsOn);
		
		Mockito.when(dealsForTodayRepository.save(Mockito.any())).thenReturn(deal);
		Assertions.assertEquals(101, dftSellerProductService.addProductInDeal(1001, "xyz@infosys.com", 5.0,LocalDateTime.parse("2020-12-12T18:00") ,LocalDateTime.parse("2020-12-12T20:00") ));
	}

	@Test
	public void viewDealsForTodayListValid() throws EKartException{
		
		String emailId="xyz@infosys.com";
		List<DealsForToday> dealList=new ArrayList<>();
		DealsForToday deal=new DealsForToday();
		dealList.add(deal);
		
		Mockito.when(dealsForTodayRepository.findBySellerEmailId(emailId)).thenReturn(dealList);
		List<DealsForTodayDTO> listDTO= dftSellerProductService.getProductInDealList(emailId);
		Assert.assertNotNull(listDTO);
	}
	
	@Test
	public void viewDealsForTodayInvalid() throws EKartException{
		
		String emailId="and@infosys.com";
		List<DealsForToday> dealList=new ArrayList<>();
		
		Mockito.when(dealsForTodayRepository.findBySellerEmailId(emailId)).thenReturn(dealList);
		EKartException e= Assertions.assertThrows(EKartException.class, ()-> dftSellerProductService.getProductInDealList(emailId));
		Assertions.assertEquals("DealsForToday.SELLER_NOT_FOUND", e.getMessage());
	}
	
	@Test
	public void viewProductNotInDealInvalid() throws EKartException{
		
		String emailId="xyz@infosys.com";
		List<Product> prodList=new ArrayList<>();
		Product product=new Product();
		prodList.add(product);
		
		Mockito.when(dealsForTodayRepository.findBySellersEmailId(emailId)).thenReturn(prodList);
		List<ProductDTO> listDTO=dftSellerProductService.getProductListNotInDeal(emailId);
		Assert.assertNotNull(listDTO);
		
	}
	
	@Test
	public void removeDealFromListValid() throws EKartException{
		
		LocalDateTime dealStartOn=LocalDateTime.parse("2020-12-12T18:00");
		LocalDateTime dealEndsOn=LocalDateTime.parse("2020-12-12T20:00");
		
		DealsForTodayDTO dealDTO=new DealsForTodayDTO();
		dealDTO.setDealId(101);
		dealDTO.setProductId(1001);
		dealDTO.setSellerEmailId("xyz@infosys.com");
		dealDTO.setDealDiscount(5.0);
		dealDTO.setDealStartsAt(dealStartOn);
		dealDTO.setDealEndsAt(dealEndsOn);
		
		DealsForToday deal=new DealsForToday();
		deal.setDealId(101);
		deal.setProductId(1001);
		deal.setSellerEmailId("xyz@infosys.com");
		deal.setDealDiscount(5.0);
		deal.setDealStartsAt(dealStartOn);
		deal.setDealEndsAt(dealEndsOn);
		
		Optional<DealsForToday> opt=Optional.of(deal);
		
		Mockito.when(dealsForTodayRepository.findById(Mockito.anyInt())).thenReturn(opt);
		Assertions.assertEquals(1001, dftSellerProductService.removeProductDeals(dealDTO));
	}

}