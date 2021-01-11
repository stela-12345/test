package com.infy.ekart.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.entity.DealsForToday;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.DealsForTodayRepository;

@Service(value = "customerDealForTodayService")
@Transactional
public class CustomerDealForTodayServiceImpl implements CustomerDealForTodayService {

	@Autowired
	private DealsForTodayRepository dealsForTodayRepository;

	public List<DealsForTodayDTO> Valid() throws EKartException {
		List<DealsForToday> dftList1 = dealsForTodayRepository.findAll();
		List<DealsForTodayDTO> productInDeal1 = null;
		if (dftList1.isEmpty())
			throw new EKartException("CustomerDealForTodayService.NO_DEAL_FOUND");
		return productInDeal1;
	}

	// Sending deals for today only
	@Override
	public List<DealsForTodayDTO> getCustomerDeals() throws EKartException {
		LocalDateTime dealStartAt = LocalDate.now().atStartOfDay();
		LocalDateTime dealEndsAt = LocalDate.now().plusDays(1).atStartOfDay();
		List<DealsForToday> dftList = dealsForTodayRepository.findByDealStartsAtAfterAndDealEndsAtBefore(dealStartAt,
				dealEndsAt);

		

		List<DealsForTodayDTO> productInDeal = new ArrayList<>();

		for (DealsForToday dft : dftList) {
			
				DealsForTodayDTO dftDTO = new DealsForTodayDTO();

				dftDTO.setDealId(dft.getDealId());
				dftDTO.setProductId(dft.getProductId());
				dftDTO.setDealDiscount(dft.getDealDiscount());
				dftDTO.setDealStartsAt(dft.getDealStartsAt());
				dftDTO.setDealEndsAt(dft.getDealEndsAt());
				dftDTO.setSellerEmailId(dft.getSellerEmailId());
				if (dft.getDealStartsAt().isBefore(LocalDateTime.now())
						&& dft.getDealEndsAt().isBefore(LocalDateTime.now())) {
					dftDTO.setStatus(2);
				} else if (dft.getDealEndsAt().isAfter(LocalDateTime.now())
						&& dft.getDealStartsAt().isBefore(LocalDateTime.now())) {
					dftDTO.setStatus(1);
				} else {
					if (dft.getDealStartsAt().isAfter(LocalDateTime.now())) {
						dftDTO.setStatus(3);
					}
				}
				productInDeal.add(dftDTO);

			//}

		}
		return productInDeal;
	}

}
