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
	
	public Company save(Company object) {
		long id = nextVal();
		object.setCompanyId(id);
		mapper.saveRecord(object);
		return object;
	}
	
	public Long nextVal() {
		return mapper.getNextVal();
	}
	
}
