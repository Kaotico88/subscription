package com.gudmundsson.subscription.core;

import java.util.ArrayList;
import java.util.List;

import com.gudmundsson.subscription.dto.ItemServiceDto;

public class Company {

	private Long companyId;
	
	private String name;
	
	private String address;
	
//	private List<ItemService> itemServices;
	private List<ItemServiceDto> itemServicedtos;
	
	public Company() {
		this.itemServicedtos = new ArrayList<>();
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

	public List<ItemServiceDto> getItemServicedtos() {
		return itemServicedtos;
	}

	public void setItemServicedtos(List<ItemServiceDto> itemServicedtos) {
		this.itemServicedtos = itemServicedtos;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", name=" + name + ", address=" + address + ", itemServicedtos="
				+ itemServicedtos + "]";
	}
	
	
}
