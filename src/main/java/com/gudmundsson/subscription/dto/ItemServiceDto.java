package com.gudmundsson.subscription.dto;

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
	private long companyId;
	
	
}
