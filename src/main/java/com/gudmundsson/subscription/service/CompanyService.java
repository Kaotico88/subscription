package com.gudmundsson.subscription.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.dao.RCompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private RCompanyRepository repository;
	
	public Company getCompanyById(Optional<Long> id) {
		return repository.getCompanyById(id);
	}
	
	public Company save(Company object) {
		return repository.save(object);
	}
}
