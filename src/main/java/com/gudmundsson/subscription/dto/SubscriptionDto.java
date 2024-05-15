package com.gudmundsson.subscription.dto;

import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.core.ItemService;
import com.gudmundsson.subscription.core.Subscription;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SubscriptionDto {

	@NotNull(message = "El campo 'state' no es válido.")
	private boolean state;
	
	@NotNull(message = "El campo 'hoursUsed' no es válido.")
	@Min(value = 0, message = "El campo 'amount' no puede ser menor a 0")
	private double hoursUsed;
	
	private Long customerId;
	
	private Long itemServiceId;
	
	public void copyToCore(Subscription object) {
		object.setState(this.state);
		object.setHoursUsed(this.hoursUsed);
		object.setCustomer(new Customer());
		object.getCustomer().setCustomerId(this.customerId);
		object.setItemService(new ItemService());
		object.getItemService().setItemServiceId(this.itemServiceId);
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public double getHoursUsed() {
		return hoursUsed;
	}

	public void setHoursUsed(double hoursUsed) {
		this.hoursUsed = hoursUsed;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getItemServiceId() {
		return itemServiceId;
	}

	public void setItemServiceId(Long itemServiceId) {
		this.itemServiceId = itemServiceId;
	}
	
	
}
