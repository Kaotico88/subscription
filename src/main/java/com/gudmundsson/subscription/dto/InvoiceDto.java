package com.gudmundsson.subscription.dto;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDto {

	private CompanyDto companyDto;
	
	private List<ItemServiceDto> itemServiceDtos;
	
	private double totalAmount;

	public InvoiceDto() { 
		this.itemServiceDtos = new ArrayList<>();
	}

	public CompanyDto getCompanyDto() {
		return companyDto;
	}

	public void setCompanyDto(CompanyDto companyDto) {
		this.companyDto = companyDto;
	}

	public List<ItemServiceDto> getItemServiceDtos() {
		return itemServiceDtos;
	}

	public void setItemServiceDtos(List<ItemServiceDto> itemServiceDtos) {
		this.itemServiceDtos = itemServiceDtos;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
