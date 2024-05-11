package com.gudmundsson.subscription.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gudmundsson.subscription.core.Invoice;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Mapper
public interface MInvoiceMapper {

	public Invoice getInvoiceById(@Param("recordId") String recordId) throws RepositoryException;
	
	public void saveRecord(@Param("invoice")Invoice invoice);
	
	public Long getNextVal();
}
