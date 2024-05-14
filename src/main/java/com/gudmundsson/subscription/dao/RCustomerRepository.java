package com.gudmundsson.subscription.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Repository
public class RCustomerRepository {

	@Autowired
	private MCustomerMapper mapper;

	public Customer getCustomerById(Optional<Long> id) throws RepositoryException {
		return mapper.getCustomerById(id.orElse(null));
	}
	
	public Customer save(Customer object) throws RepositoryException {
		long id = nextVal();
		object.setCustomerId(id);
		mapper.saveRecord(object);
		return object;
	}
	
	public Long nextVal() {
		return mapper.getNextVal();
	}
}
