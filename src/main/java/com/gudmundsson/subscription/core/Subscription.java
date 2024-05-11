package com.gudmundsson.subscription.core;

import java.sql.Date;

public class Subscription {

	private String subscriptionId;
	
	private Date activationDate;
	
	private Boolean state;
	
	private Double hoursUsed;
	
	private User user;
	
	private ItemService itemService;

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	
}
