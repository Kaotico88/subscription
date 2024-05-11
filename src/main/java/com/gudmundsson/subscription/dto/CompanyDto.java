package com.gudmundsson.subscription.dto;

import com.gudmundsson.subscription.core.Company;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CompanyDto {

	@NotNull(message = "EL campo 'name' no es valido.")
	@Size(max = 3, message = "El campo 'name' no debe ser mayor que de 40 caracteres.")
	private String name;
	
	@NotNull(message = "EL campo 'address' no es valido.")
	@Size(max = 3, message = "El campo 'address' no debe ser mayor que de 40 caracteres.")
	private String address;
	
	public void copyToCore(Company object) {
		object.setName(this.name);
		object.setAddress(this.address);
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
	
}
