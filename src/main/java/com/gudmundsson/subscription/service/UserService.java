package com.gudmundsson.subscription.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.User;
import com.gudmundsson.subscription.dao.RUserRepository;

@Service
public class UserService {

	@Autowired
	private RUserRepository repository;
	
	public User getUserById(Optional<Long> id) {
		return repository.getUserById(id);
	}
	
	public User save(User object) {
		return repository.save(object);
	}
}
