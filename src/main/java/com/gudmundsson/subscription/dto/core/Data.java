package com.gudmundsson.subscription.dto.core;

import java.util.ArrayList;
import java.util.List;

import com.gudmundsson.subscription.core.Company;
//import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.dto.CustomerDto;


public class Data {

	private CustomerDto customerDto;
	
	private List<Company> companies;
	
	public Data() {
		this.companies = new ArrayList<>();
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
