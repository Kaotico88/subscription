package com.gudmundsson.subscription.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.User;
import com.gudmundsson.subscription.dao.RUserRepository;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Service
public class UserService {

	@Autowired
	private RUserRepository repository;
	
	public User getUserById(Optional<Long> id) throws RepositoryException{
		return repository.getUserById(id);
	}
	
	public User save(User object) throws RepositoryException{
		System.out.println("Estoy en el metodo save de user!!");
		return repository.save(object);
	}
}
