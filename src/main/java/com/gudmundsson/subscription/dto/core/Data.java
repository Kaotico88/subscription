package com.gudmundsson.subscription.dto.core;

import java.util.ArrayList;
import java.util.List;

import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.core.Invoice;


public class Data {

	private Customer customer;
	
	private List<Invoice> invoices;
	
	public Data() {
		this.invoices = new ArrayList<>();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoiceDto(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	
}
