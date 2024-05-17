package com.gudmundsson.subscription.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.dao.RCompanyRepository;
import com.gudmundsson.subscription.dao.RCustomerRepository;
import com.gudmundsson.subscription.dao.RInvoiceRepository;
import com.gudmundsson.subscription.dao.RSubscriptionRepository;
import com.gudmundsson.subscription.dto.ResponseInvoiceDto;
import com.gudmundsson.subscription.dto.core.Data;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Service
public class ResponseInvoiceService {
	
	@Autowired
	private CustomerService customerService;

	public ResponseInvoiceDto getInvoiceDetails(Optional<Long> invoiceId) throws RepositoryException {
		
		ResponseInvoiceDto responseObject = new ResponseInvoiceDto();
		responseObject.setData(new Data());
		responseObject.getData().setCustomer(customerService.getCustomerByInvoiceId(invoiceId));
		
		return responseObject;
		
	}
}
