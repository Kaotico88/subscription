package com.gudmundsson.subscription.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.core.Invoice;
import com.gudmundsson.subscription.core.Subscription;
import com.gudmundsson.subscription.dao.RCompanyRepository;
import com.gudmundsson.subscription.dao.RCustomerRepository;
import com.gudmundsson.subscription.dao.RInvoiceRepository;
import com.gudmundsson.subscription.dao.RSubscriptionRepository;
import com.gudmundsson.subscription.dto.ResponseInvoiceDto;

@Service
public class ResponseInvoiceService {

	@Autowired
	private RInvoiceRepository invoiceRepository;
	
	@Autowired
	private RCustomerRepository customerRepository;
	
	@Autowired
	private RCompanyRepository companyRepository;
	
	@Autowired
	private RSubscriptionRepository subscriptionRepository;
	
//	@Autowired
//	private InvoiceService invoiceService;
	
	public ResponseInvoiceDto getInvoiceDetails(Optional<Long> id) {
		
		Invoice invoice = invoiceRepository.getInvoiceById(id);

		Customer customer = customerRepository.getCustomerById(Optional.of(invoice.getSubscription().getCustomer().getCustomerId()));
		
		Company company = companyRepository.getCompanyById(Optional.of(invoice.getSubscription().getItemService().getCompany().getCompanyId()));
		
		List<Subscription> subscriptions = subscriptionRepository.getSubscriptionsByCustomerId(Optional.of(customer.getCustomerId()));
		
	}
}
