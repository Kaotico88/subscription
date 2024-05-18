package com.gudmundsson.subscription.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.core.ItemService;
import com.gudmundsson.subscription.dto.CustomerDto;
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

	public ResponseInvoiceDto getInvoiceDetails(Optional<Long> invoiceId, String sessionLogId)
			throws RepositoryException {

		ResponseInvoiceDto responseObject = new ResponseInvoiceDto();
		responseObject.setData(new Data());

//		responseObject.getData().setCustomer(customerService.getCustomerByInvoiceId(invoiceId));
		
		Customer customer = customerService.getCustomerByInvoiceId(invoiceId);
		CustomerDto customerDto = new CustomerDto();
		customerDto.setName(customer.getName());
		customerDto.setEmail(customer.getEmail());
		
		responseObject.getData().setCustomerDto(customerDto);

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
			company.setItemServicedtos(itemServiceDtos);
		}

		responseObject.getData().setCompanies(companies);

		return responseObject;
	}
}
