package com.gudmundsson.subscription.core;

public class ItemService {

	private Long itemServiceId;
	
	private String name;
	
	private Double costHour;
	
	private String description;
	
	private Company company;

	public Long getItemServiceId() {
		return itemServiceId;
	}

	public void setItemServiceId(Long itemServiceId) {
		this.itemServiceId = itemServiceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCostHour() {
		return costHour;
	}

	public void setCostHour(Double costHour) {
		this.costHour = costHour;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "ItemService [itemServiceId=" + itemServiceId + ", name=" + name + ", costHour=" + costHour
				+ ", description=" + description + ", company=" + company + "]";
	}
	
	
}
