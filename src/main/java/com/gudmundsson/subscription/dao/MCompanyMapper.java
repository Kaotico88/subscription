package com.gudmundsson.subscription.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Mapper
public interface MCompanyMapper {

	public Company getCompanyById(@Param("recordId") String recordId) throws RepositoryException;
	
	public void saveRecord(@Param("company") Company company);
	
	public Long getNextVal();
}
