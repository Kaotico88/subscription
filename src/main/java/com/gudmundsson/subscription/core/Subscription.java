package com.gudmundsson.subscription.core;

public class Subscription {

	private String subscriptioId;
	
	private String activationDate;
	
	private Boolean state;
	
	private Double hoursUsed;
	
	private User user;
	
	private ItemService itemservice;

	public String getSubscriptioId() {
		return subscriptioId;
	}

	public void setSubscriptioId(String subscriptioId) {
		this.subscriptioId = subscriptioId;
	}

	public String getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(String activationDate) {
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

	public ItemService getItemservice() {
		return itemservice;
	}

	public void setItemservice(ItemService itemservice) {
		this.itemservice = itemservice;
	}
	
	
}
