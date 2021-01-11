package com.infy.ekart.service.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.entity.DealsForToday;
import com.infy.ekart.repository.DealsForTodayRepository;
import com.infy.ekart.service.CustomerDealForTodayService;
import com.infy.ekart.service.CustomerDealForTodayServiceImpl;

@SpringBootTest
public class CustomerDealForTodayTest {
	 
	@Mock
	private DealsForTodayRepository dftRepository;
	
	@InjectMocks 
	private CustomerDealForTodayService customerDealsForTodayService= new CustomerDealForTodayServiceImpl();
	
	
	@Test
	public void getDealsValid() throws Exception {
		List<DealsForToday> dealList= new ArrayList<>();
		DealsForToday deal=new DealsForToday();
		deal.setDealId(4000);
		deal.setDealStartsAt(LocalDateTime.now());
		deal.setProductId(1001);
		dealList.add(deal);
		
		DealsForTodayDTO d1=new DealsForTodayDTO();
		d1.setStatus(1);
		d1.getStatus();
		d1.getDealDiscount();
		d1.getDealEndsAt();
		d1.getDealStartsAt();
		d1.getDealId();
		d1.getSellerEmailId();
		d1.getProductId();
		
		
		Mockito.when(dftRepository.findByDealStartsAtAfterAndDealEndsAtBefore(Mockito.any(), Mockito.any())).thenReturn(dealList);
		List<DealsForTodayDTO> listDTO=customerDealsForTodayService.getCustomerDeals();
		DealsForTodayDTO d2=new DealsForTodayDTO();
		d2.getStatus();
		Assert.assertNotNull(listDTO);
		
	}

	@Test
	public void getDealsInvalid() throws Exception {
		List<DealsForToday> dealList= new ArrayList<>();
		DealsForToday deal=new DealsForToday();
		deal.setDealId(4000);
		deal.setDealStartsAt(LocalDateTime.now().minusDays(1));
		deal.setProductId(1001);
		dealList.add(deal);
		
		
		Exception e=Assertions.assertThrows(Exception.class, ()->customerDealsForTodayService.Valid());
		Assertions.assertEquals("CustomerDealForTodayService.NO_DEAL_FOUND",e.getMessage());
	}
	
	

}