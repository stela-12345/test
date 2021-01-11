package com.infy.ekart.main;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.ekart.EKartApplication;
@SpringBootTest
public class MyApplicationIT {
	@Test
	public void main()
	{
		EKartApplication.main(new String[] {});
	}

}
