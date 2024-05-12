package com.gudmundsson.subscription.dto;



import com.gudmundsson.subscription.core.Customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerDto {

	@NotNull(message = "El campo 'name' no es válido.")
    @Size(max = 3, message = "El campo 'name' no debe ser mayor que 40 caracteres.")
	private String name;
	
	@NotNull(message = "El campo 'email' no es válido.")
    @Size(max = 3, message = "El campo 'email' no debe ser mayor que 40 caracteres.")
	private String email;
	
	public void copyToCore(Customer object) {
		object.setName(this.name);
		object.setEmail(this.email);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
