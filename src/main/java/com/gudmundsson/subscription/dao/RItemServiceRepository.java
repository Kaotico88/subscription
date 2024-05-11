package com.gudmundsson.subscription.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gudmundsson.subscription.core.ItemService;

@Repository
public class RItemServiceRepository {

	@Autowired
	private MItemServiceMapper mapper;

	public ItemService getById(Optional<String> id) {
		return mapper.getItemServiceById(id.orElse(null));
	}
	
	public ItemService save(ItemService object) {
		long id = nextVal();
		object.setUid(uid);
		mapper.saveRecord(object);
		return object;
	}
}
