package com.gudmundsson.subscription.core;

import java.util.ArrayList;
import java.util.List;

public class Company {

	private Long companyId;
	
	private String name;
	
	private String address;
	
	private List<ItemService> itemServices;
	
	public Company() {
		this.itemServices = new ArrayList<>();
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<ItemService> getItemServices() {
		return itemServices;
	}

	public void setItemServices(List<ItemService> itemServices) {
		this.itemServices = itemServices;
	}
	
	
}
