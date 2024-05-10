package com.gudmundsson.subscription.core;

public class User {

	private String userId;
	
	private String name;
	
	private String email;
	
	private Double totalCostMonth;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
