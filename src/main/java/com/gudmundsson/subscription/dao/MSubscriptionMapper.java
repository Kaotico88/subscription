package com.gudmundsson.subscription.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gudmundsson.subscription.core.Subscription;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Mapper
public interface MSubscriptionMapper {

	public Subscription getSubscriptionById(@Param("recordId") long recordId) throws RepositoryException;

	public List<Subscription> getSubscriptionsByCustomerId(@Param("recordId") long recordId) throws RepositoryException;

	public List<Subscription> getSubscriptionsByInvoiceId(@Param("invoiceId") long invoiceId)
			throws RepositoryException;

	public void saveRecord(@Param("subscription") Subscription subscription) throws RepositoryException;

	public void updateRecord(@Param("subscription") Subscription subscription) throws RepositoryException;

	public Long getNextVal();
}
