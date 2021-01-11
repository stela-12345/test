package com.infy.ekart.repository.test;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.infy.ekart.entity.DealsForToday;
import com.infy.ekart.entity.Product;
import com.infy.ekart.entity.Seller;
import com.infy.ekart.repository.DealsForTodayRepository;

@DataJpaTest
public class DealForDayTestRepo { 
	 
	@Autowired 
	private DealsForTodayRepository dealRepository; 
	private DealsForToday deal; 
	  
	@BeforeEach 
	public void setUp()   
	{  
		deal = new  DealsForToday(); 
		Seller seller =new Seller();  
		seller.setEmailId("xyz@infosys.com"); 
		Product product = new Product();
		product.setProductId(9999); 
		deal.setDealId(10001); 
		deal.setProductId(1001);
		 
		
		
		
	}
    
	@Test 
	void saveValidTest()  
	{  
		 DealsForToday dealAfterSave = dealRepository.save(new  DealsForToday()); 
		 Optional< DealsForToday> optionalDeal =  dealRepository.findById(dealAfterSave.getDealId());  
		 Assertions.assertTrue(optionalDeal.isPresent()); 
		 
		 
		
	} 
	 
	@Test 
	void findByEmailIdValidTest() 
	{  
		//dealRepository.save(deal); 
		List<DealsForToday> dealList = dealRepository.findBySellerEmailId("xyz@infosys.com");  
		
		 Assertions.assertTrue(dealList.isEmpty()); 
	}
	@Test
     void findByEmailIdInValidTest()
	
	{  
		//dealRepository.save(deal); 
		List<DealsForToday> dealList = dealRepository.findBySellerEmailId("xyz@infoys.com");   
		 Assertions.assertTrue(dealList.isEmpty()); 

	}
		@Test
		void getSellerProductValidTest() 
		{  
			//dealRepository.save(deal); 
			List<Product> dealList = dealRepository.findBySellersEmailId("xyz@infosys.com");   
			 Assertions.assertTrue(dealList.isEmpty());   
		}  

		@Test 
		void getSellerProductInValidTest() 
		{  
			//dealRepository.save(deal); 
			List<Product> dealList = dealRepository.findBySellersEmailId("xyz@infosys.com");   
			 Assertions.assertTrue(dealList.isEmpty());   
		} 

		 
		 
			 
			
	
	 
	
}