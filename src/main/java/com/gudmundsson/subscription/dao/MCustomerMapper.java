package com.gudmundsson.subscription.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Mapper
public interface MCustomerMapper {

	public Customer getCustomerById(@Param("recordId") Long recordId) throws RepositoryException;
	
	public void saveRecord(@Param("customer") Customer customer) throws RepositoryException;
	
	public Long getNextVal();
}
