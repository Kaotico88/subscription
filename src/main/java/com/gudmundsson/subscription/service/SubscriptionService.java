package com.gudmundsson.subscription.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.Subscription;
import com.gudmundsson.subscription.dao.RSubscriptionRepository;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Service
public class SubscriptionService {

	@Autowired
	private RSubscriptionRepository repository;

	public Subscription getSubscriptionById(Optional<Long> id) throws RepositoryException {
		return repository.getSubscriptionById(id);
	}

	public Subscription save(Subscription object) throws RepositoryException {
		return repository.save(object);
	}
	
	public Subscription update(Subscription object) throws RepositoryException{
		return repository.update(object);
	}
	
}
