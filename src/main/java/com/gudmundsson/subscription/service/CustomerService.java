package com.gudmundsson.subscription.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.dao.RCustomerRepository;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Service
public class CustomerService {

	@Autowired
	private RCustomerRepository repository;
	
	public Customer getCustomerById(Optional<Long> id) throws RepositoryException{
		return repository.getCustomerById(id);
	}
	
	public Customer save(Customer object) throws RepositoryException{
		return repository.save(object);
	}
	
	public Customer getCustomerByInvoiceId(Optional<Long> invoiceId) throws RepositoryException{
		return repository.getCustomerByInvoiceId(invoiceId);
	}
}
