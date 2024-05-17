package com.gudmundsson.subscription.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.dao.RCompanyRepository;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Service
public class CompanyService {

	@Autowired
	private RCompanyRepository repository;
	
	public Company getCompanyById(Optional<Long> id) throws RepositoryException{
		return repository.getCompanyById(id);
	}
	
	public Company save(Company object) throws RepositoryException{
		return repository.save(object);
	}
	
	public List<Company> getCompaniesByInvoiceId(Optional<Long> invoiceId) throws RepositoryException{
		return repository.getCompaniesByInvoiceId(invoiceId);
	}
}
