package com.gudmundsson.subscription.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.core.ItemService;
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

	public ResponseInvoiceDto getInvoiceDetails(Optional<Long> invoiceId, String sessionLogId) throws RepositoryException {
		
		ResponseInvoiceDto responseObject = new ResponseInvoiceDto();
		responseObject.setData(new Data());
		
		responseObject.getData().setCustomer(customerService.getCustomerByInvoiceId(invoiceId));
		
		List<Company> companies = companyService.getCompaniesByInvoiceId(invoiceId);
		
		responseObject.getData().setCompanies(companies);
//		Lista de itemservices a partir de una companyId que sera el for que recorra esa lista
		for(Company company : companies) {
			List<ItemService> itemServices = itemServiceService.getItemServicesByCompanyId(Optional.of(company.getCompanyId()));
			List<Long> itemServiceIds = itemServices.stream().map(ItemService::getItemServiceId).collect(Collectors.toList());
			for(Long itemServiceId : itemServiceIds) {
				ItemService itemService = itemServiceService.getItemServiceById(Optional.of(itemServiceId));
				itemServices.add(itemService);
			}
		}

		responseObject.getData().setCompanies(companies);

		return responseObject;	
	}
}
