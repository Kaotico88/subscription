package com.gudmundsson.subscription.core;

import java.sql.Timestamp;

public class Subscription {

	private Long subscriptionId;
	
	private Boolean state;
	
	private Double hoursUsed;
	
	private Timestamp activationDate;
	
	private Customer customer;
	
	private ItemService itemService;

	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Double getHoursUsed() {
		return hoursUsed;
	}

	public void setHoursUsed(Double hoursUsed) {
		this.hoursUsed = hoursUsed;
	}

	public Timestamp getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Timestamp activationDate) {
		this.activationDate = activationDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	
}
