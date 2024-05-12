package com.gudmundsson.subscription.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.ItemService;
import com.gudmundsson.subscription.dao.RItemServiceRepository;

@Service
public class ItemServiceService {

	@Autowired
	private RItemServiceRepository repository;
	
	public ItemService getItemServiceById(Optional<Long> id) {
		return repository.getItemServiceById(id);
	}
	
	public ItemService save(ItemService object) {
		return repository.save(object);
	}
	
}
