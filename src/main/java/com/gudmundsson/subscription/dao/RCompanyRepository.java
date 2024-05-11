package com.gudmundsson.subscription.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gudmundsson.subscription.core.Company;

@Repository
public class RCompanyRepository {

	@Autowired
	private MCompanyMapper mapper;
	
	public Company getById(Optional<String> id) {
		return mapper.getCompanyById(id.orElse(null));
	}
}
