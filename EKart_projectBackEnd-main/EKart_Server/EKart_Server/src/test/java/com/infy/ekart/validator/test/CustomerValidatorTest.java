package com.infy.ekart.validator.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.infy.ekart.dto.CustomerDTO;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.validator.CustomerValidator;

class CustomerValidatorTest {

	private CustomerDTO customerDTO;

	@BeforeEach
	public void setUp() {
		customerDTO = new CustomerDTO();
		customerDTO.setEmailId("fahad@infosys.com");
		customerDTO.setNewPassword("fahad@123");
	}

	@Test
	void validatePasswordsForSellerChangePasswordValidTest() {
		customerDTO.setConfirmNewPassword("fahad@123");
		Assertions.assertEquals(customerDTO.getNewPassword(), customerDTO.getConfirmNewPassword());
	}
	
	@Test
	void validatePasswordsForSellerChangePasswordInvalidTest() {
		customerDTO.setConfirmNewPassword("fahad@12");
		Exception exception = Assertions.assertThrows(EKartException.class,
				() -> CustomerValidator.validatePasswordsForCustomerChangePassword(customerDTO));
		Assertions.assertEquals("SellerValidator.NEW_PASSWORD_AND_CONFIRM_NEW_PASSWORD_MISMATCH", exception.getMessage());
	}
}
