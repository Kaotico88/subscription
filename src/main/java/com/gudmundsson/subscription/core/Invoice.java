package com.gudmundsson.subscription.core;

import java.sql.Timestamp;

public class Invoice {

	private Long invoiceId;
	
	private String billingPeriod;
	
	private Timestamp issueDate;
	
	private Double totalAmount;
	
	private Subscription subscription;

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getBillingPeriod() {
		return billingPeriod;
	}

	public void setBillingPeriod(String billingPeriod) {
		this.billingPeriod = billingPeriod;
	}

	public Timestamp getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", billingPeriod=" + billingPeriod + ", issueDate=" + issueDate
				+ ", totalAmount=" + totalAmount + ", subscription=" + subscription + "]";
	}

		
}
