package com.gudmundsson.subscription.dto.core;

import java.util.ArrayList;
import java.util.List;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.core.Invoice;
//import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.dto.CustomerDto;


public class Data {

	private Invoice invoice;
	
	private CustomerDto customer;
	
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

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
}
