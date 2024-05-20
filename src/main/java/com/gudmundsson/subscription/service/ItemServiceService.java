package com.gudmundsson.subscription.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.ItemService;
import com.gudmundsson.subscription.dao.RItemServiceRepository;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Service
public class ItemServiceService {

	@Autowired
	private RItemServiceRepository repository;
	
	public ItemService getItemServiceById(Optional<Long> id)throws RepositoryException{
		return repository.getItemServiceById(id);
	}
	
	public ItemService save(ItemService object) throws RepositoryException{
		return repository.save(object);
	}
	
	public List<ItemService> getItemServicesByCompanyId(Optional<Long> companyId) throws RepositoryException{
		return repository.getItemServicesByCompanyId(companyId);
	}
	
	public ItemService getItemServiceBySubscriptionId(Optional<Long> subscriptionId) throws RepositoryException{
		return repository.getItemServiceBySubscriptionId(subscriptionId);
	}
}
