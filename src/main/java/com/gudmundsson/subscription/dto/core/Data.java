package com.gudmundsson.subscription.dto.core;

import java.util.ArrayList;
import java.util.List;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.core.Invoice;
//import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.dto.CustomerDto;
import com.gudmundsson.subscription.dto.InvoiceDto;
import com.gudmundsson.subscription.dto.InvoiceDto2;


public class Data {

	private InvoiceDto2 invoice;
	
	private CustomerDto customer;
	
	private List<Company> companies;
	
	public Data() {
		this.companies = new ArrayList<>();
	}
	
	

	public InvoiceDto2 getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceDto2 invoice) {
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
