package com.gudmundsson.subscription.core;

import java.util.ArrayList;
import java.util.List;

import com.gudmundsson.subscription.dto.ItemServiceDto;

public class Company {

	private Long companyId;
	
	private String name;
	
	private String address;
	
	private List<ItemServiceDto> itemServiceDtos;
	
	public Company() {
		this.itemServiceDtos = new ArrayList<>();
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

	public List<ItemServiceDto> getItemServiceDtos() {
		return itemServiceDtos;
	}

	public void setItemServiceDtos(List<ItemServiceDto> itemServiceDtos) {
		this.itemServiceDtos = itemServiceDtos;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", name=" + name + ", address=" + address + ", itemServiceDtos="
				+ itemServiceDtos + "]";
	}
	
	
}
