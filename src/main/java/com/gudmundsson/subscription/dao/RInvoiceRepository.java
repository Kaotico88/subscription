package com.gudmundsson.subscription.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gudmundsson.subscription.core.Invoice;

@Repository
public class RInvoiceRepository {
	
	@Autowired
	private MInvoiceMapper mapper;

	public Invoice getById(Optional<String> id) {
		return mapper.getInvoiceById(id.orElse(null));
	}
	
	public Invoice save(Invoice object) {
		long id = nextVal();
		object.setInvoiceId(id);
		mapper.saveRecord(object);
		return object;
	}
	
	public Long nextVal() {
		return mapper.getNextVal();
	}
}
