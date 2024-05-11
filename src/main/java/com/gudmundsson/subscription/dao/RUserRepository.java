package com.gudmundsson.subscription.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gudmundsson.subscription.core.User;

@Repository
public class RUserRepository {

	@Autowired
	private MUserMapper mapper;

	public User getById(Optional<String> id) {
		return mapper.getUserById(id.orElse(null));
	}
	
	public User save(User object) {
		long id = nextVal();
		object.setUserId(id);
		mapper.saveRecord(object);
		return object;
	}
	
	public Long nextVal() {
		return mapper.getNextVal();
	}
}
