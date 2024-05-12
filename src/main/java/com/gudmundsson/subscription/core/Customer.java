package com.gudmundsson.subscription.core;

public class Customer {

	private Long customerId;
	
	private String name;
	
	private String email;
	
	private Double totalCostMonth;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getTotalCostMonth() {
		return totalCostMonth;
	}

	public void setTotalCostMonth(Double totalCostMonth) {
		this.totalCostMonth = totalCostMonth;
	}

	
	
}
