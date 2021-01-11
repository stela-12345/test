package com.infy.ekart.validator.test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



import com.infy.ekart.validator.DealsForTodayValidator;

public class DealsForTodayValidatorTest {
	
	@Test
	public void DateInvalid() throws Exception {
	
		LocalDateTime dealStartOn=LocalDateTime.parse("2021-01-09T18:00");
		LocalDateTime dealEndsOn=LocalDateTime.parse("2020-01-08T20:00");
		
		
		Exception e=Assertions.assertThrows(Exception.class, ()->DealsForTodayValidator.validateProductForDeal(dealStartOn, dealEndsOn));
		Assertions.assertEquals("DealsForTodayValidator.INVALID_DATE",e.getMessage());
		
		
	}
	
	
	
	

}
