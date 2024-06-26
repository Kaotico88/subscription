package com.gudmundsson.subscription.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gudmundsson.subscription.core.Subscription;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Repository
public class RSubscriptionRepository {

	@Autowired
	private MSubscriptionMapper mapper;

	public Subscription getSubscriptionById(Optional<Long> id) throws RepositoryException {
		return mapper.getSubscriptionById(id.orElse(null));
	}

	public List<Subscription> getSubscriptionsByCustomerId(Optional<Long> id) throws RepositoryException {
		return mapper.getSubscriptionsByCustomerId(id.orElse(null));
	}

	public List<Subscription> getSubscriptionsByInvoiceId(Optional<Long> invoiceId) throws RepositoryException {
		return mapper.getSubscriptionsByInvoiceId(invoiceId.orElse(null));
	}

	public Subscription save(Subscription object) throws RepositoryException {
		long id = nextVal();
		object.setSubscriptionId(id);
		mapper.saveRecord(object);
		return object;
	}

	public Subscription update(Subscription object) {
		mapper.updateRecord(object);
		return object;
	}

	public Long nextVal() {
		return mapper.getNextVal();
	}
}
