package com.gudmundsson.subscription.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gudmundsson.subscription.core.Invoice;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Repository
public class RInvoiceRepository {
	
	@Autowired
	private MInvoiceMapper mapper;

	public Invoice getById(Optional<Long> id) throws RepositoryException {
		return mapper.getInvoiceById(id.orElse(null));
	}
	
	public Invoice save(Invoice object) throws RepositoryException {
		long id = nextVal();
		object.setInvoiceId(id);
		mapper.saveRecord(object);
		return object;
	}
	
	public Long nextVal() {
		return mapper.getNextVal();
	}
}
