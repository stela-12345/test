package com.infy.ekart.validator;

import com.infy.ekart.dto.CustomerDTO;
import com.infy.ekart.exception.EKartException;

public class CustomerValidator {
		
	public static void validatePasswordsForCustomerChangePassword(CustomerDTO customerDTO) throws EKartException {
		if(!customerDTO.getNewPassword().equals(customerDTO.getConfirmNewPassword()))
			throw new EKartException("SellerValidator.NEW_PASSWORD_AND_CONFIRM_NEW_PASSWORD_MISMATCH");
	}
	
}
