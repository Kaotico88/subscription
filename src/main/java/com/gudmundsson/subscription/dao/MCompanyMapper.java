package com.gudmundsson.subscription.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Mapper
public interface MCompanyMapper {

	public Company getCompanyById(@Param("recordId") Long recordId) throws RepositoryException;
	
	public void saveRecord(@Param("company") Company company) throws RepositoryException;
	
	public List<Company> getCompaniesByInvoiceId(@Param("invoiceId") Long invoiceId) throws RepositoryException; 
	
	public Long getNextVal();
}
