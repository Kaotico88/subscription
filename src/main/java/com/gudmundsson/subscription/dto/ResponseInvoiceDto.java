package com.gudmundsson.subscription.dto;

import java.util.List;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.core.Invoice;
import com.gudmundsson.subscription.core.Subscription;

public class ResponseInvoiceDto {

	private Customer customer;
	
	private Company company;
	
	private Invoice invoice;
	
	private double total;
	
	private List<Subscription> subscriptions;

	public ResponseInvoiceDto() {
	}

	public ResponseInvoiceDto(Customer customer, Company company, Invoice invoice, List<Subscription> subscriptions) {
		this.customer = customer;
		this.company = company;
		this.invoice = invoice;
		this.subscriptions = subscriptions;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
	
}
