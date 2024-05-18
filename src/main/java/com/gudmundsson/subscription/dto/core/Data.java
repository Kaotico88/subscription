package com.gudmundsson.subscription.dto.core;

import java.util.ArrayList;
import java.util.List;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.core.Invoice;
//import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.dto.CustomerDto;


public class Data {

	private Invoice invoice;
	
	private CustomerDto customerDto;
	
	private List<Company> companies;
	
	public Data() {
		this.companies = new ArrayList<>();
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public CustomerDto getCustomer() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
}
