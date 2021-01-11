package com.infy.ekart.validator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import com.infy.ekart.exception.EKartException;
//added by manish
public class DealsForTodayValidator {
	
	public static void validateProductForDeal(LocalDateTime startDateTime, LocalDateTime endDateTime) throws EKartException{
		if(!isValidDate(startDateTime, endDateTime)) {
			throw new EKartException("DealsForTodayValidator.INVALID_DATE");
		}
		else {
			if(!isValidTime(startDateTime, endDateTime)) {
				throw new EKartException("DealsForTodayValidator.INVALID_TIME");
			}
		}
	}
	
	private static Boolean isValidDate(LocalDateTime startDate, LocalDateTime endDate) {
		LocalDateTime today= LocalDateTime.now();
		if(startDate.get(ChronoField.DAY_OF_MONTH)==endDate.get(ChronoField.DAY_OF_MONTH) && startDate.get(ChronoField.DAY_OF_MONTH)>=today.get(ChronoField.DAY_OF_MONTH)) {
			long between1=ChronoUnit.MONTHS.between(today, startDate);
			long between2=ChronoUnit.MONTHS.between(today, endDate);
			if(between1<=1 && between2<=1) {
				return true;
			}
		}
		return false;
	}
	
	private static Boolean isValidTime(LocalDateTime startTime, LocalDateTime endTime) {
		if(endTime.isAfter(startTime)){
			return true;
		}
		return false;
	}

}
