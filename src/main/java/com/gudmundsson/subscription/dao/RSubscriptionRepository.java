package com.gudmundsson.subscription.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gudmundsson.subscription.core.Subscription;

@Repository
public class RSubscriptionRepository {

	@Autowired
	private MSubscriptionMapper mapper;

	public Subscription getSubscriptionById(Optional<Long> id) {
		return mapper.getSubscriptionById(id.orElse(null));
	}
	
	public Subscription save(Subscription object) {
		long id = nextVal();
		object.setSubscriptionId(id);
		mapper.saveRecord(object);
		return object;
	}
	
	public Long nextVal() {
		return mapper.getNextVal();
	}
}
