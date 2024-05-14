package com.gudmundsson.subscription.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gudmundsson.subscription.core.ItemService;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Repository
public class RItemServiceRepository {

	@Autowired
	private MItemServiceMapper mapper;

	public ItemService getItemServiceById(Optional<Long> id) throws RepositoryException {
		return mapper.getItemServiceById(id.orElse(null));
	}
	
	public ItemService save(ItemService object) throws RepositoryException {
		long id = nextVal();
		object.setItemServiceId(id);
		mapper.saveRecord(object);
		return object;
	}
	
	public Long nextVal() {
		return mapper.getNextVal();
	}
}
