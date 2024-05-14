package com.gudmundsson.subscription.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gudmundsson.subscription.core.ItemService;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Mapper
public interface MItemServiceMapper {

	public ItemService getItemServiceById(@Param("recordId") Long recordId) throws RepositoryException;
	
	public void saveRecord(@Param("item_service") ItemService itemService) throws RepositoryException;
	
	public Long getNextVal();
}
