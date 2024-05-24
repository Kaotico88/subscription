package com.gudmundsson.subscription.dao;

import java.util.List;
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

	public List<ItemService> getItemServicesByCompanyId(Optional<Long> companyId) throws RepositoryException {
		return mapper.getItemServicesByCompanyId(companyId.orElse(null));
	}

	public ItemService getItemServiceBySubscriptionId(Optional<Long> subscriptionId) throws RepositoryException {
		return mapper.getItemServiceBySubscriptionId(subscriptionId.orElse(null));
	}

	public Long nextVal() {
		return mapper.getNextVal();
	}
}
