package com.gudmundsson.subscription.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.core.Invoice;
import com.gudmundsson.subscription.core.ItemService;
import com.gudmundsson.subscription.dto.CustomerDto;
import com.gudmundsson.subscription.dto.InvoiceDto2;
import com.gudmundsson.subscription.dto.ItemServiceDto;
import com.gudmundsson.subscription.dto.ResponseInvoiceDto;
import com.gudmundsson.subscription.dto.core.Data;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Service
public class ResponseInvoiceService {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ItemServiceService itemServiceService;
	
	@Autowired
	private InvoiceService invoiceservice;

	public ResponseInvoiceDto getInvoiceDetails(Optional<Long> invoiceId, String sessionLogId)
			throws RepositoryException {

		ResponseInvoiceDto responseObject = new ResponseInvoiceDto();
		responseObject.setData(new Data());
		
//		Empezamos a settear el invoice con los datos que se require
		Invoice invoice = invoiceservice.getInvoiceById(invoiceId);
		InvoiceDto2 invoiceDto2 = new InvoiceDto2();
		invoiceDto2.setBillingPeriod(invoice.getBillingPeriod());
		invoiceDto2.setIssueDate(invoice.getIssueDate());
		invoiceDto2.setSubscriptionId(invoice.getSubscription().getSubscriptionId());
		
		
		responseObject.getData().setInvoice(invoiceDto2);
		
//		Este "customer" es un Dto por si caso....
		CustomerDto customer = new CustomerDto();
		customer.setName(customerService.getCustomerByInvoiceId(invoiceId).getName());
		customer.setEmail(customerService.getCustomerByInvoiceId(invoiceId).getEmail());
		responseObject.getData().setCustomer(customer);

		List<Company> companies = companyService.getCompaniesByInvoiceId(invoiceId);

		for (Company company : companies) {
			List<ItemService> itemServicesxCompany = itemServiceService
					.getItemServicesByCompanyId(Optional.of(company.getCompanyId()));
			
			List<ItemServiceDto> itemServiceDtos = new ArrayList<>();

			for (ItemService itemService : itemServicesxCompany) {
				ItemServiceDto itemServiceDto = new ItemServiceDto();
				itemServiceDto.setName(itemService.getName());
				itemServiceDto.setCostHour(itemService.getCostHour());
				itemServiceDto.setDescription(itemService.getDescription());
				itemServiceDto.setCompanyId(itemService.getCompany().getCompanyId());
				itemServiceDtos.add(itemServiceDto);
			}
			company.setItemServiceDtos(itemServiceDtos);
		}
		
		responseObject.getData().setCompanies(companies);

		return responseObject;
	}
}
