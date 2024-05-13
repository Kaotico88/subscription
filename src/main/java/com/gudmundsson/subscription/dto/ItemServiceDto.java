package com.gudmundsson.subscription.dto;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.core.ItemService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ItemServiceDto {

	@NotNull(message = "EL campo 'name' no es valido.")
	@Size(max = 3, message = "El campo 'name' no debe ser mayor que de 40 caracteres.")
	private String name;
	
	@NotNull(message = "EL campo 'costHour' no es valido.")
	@Size(max = 5, message = "El campo 'costHour' no debe ser mayor que de 5 caracteres.")
	private double costHour;
	
	@NotNull(message = "EL campo 'description' no es valido.")
	@Size(max = 50, message = "El campo 'description' no debe ser mayor que de 50 caracteres.")
	private String description;
	
	@NotNull(message = "EL campo 'companyId' no es valido.")
	@Min(value = 1, message = "El id no puede ser menor que 1.")
	private long companyId;
	
	public void copyToCore(ItemService object) {
		object.setName(this.name);
		object.setCostHour(this.costHour);
		object.setDescription(this.description);
		object.setCompany(new Company());
		object.getCompany().setCompanyId(this.companyId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCostHour() {
		return costHour;
	}

	public void setCostHour(double costHour) {
		this.costHour = costHour;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	
}
