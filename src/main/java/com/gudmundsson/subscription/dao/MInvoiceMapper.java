package com.gudmundsson.subscription.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gudmundsson.subscription.core.Invoice;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Mapper
public interface MInvoiceMapper {

	public Invoice getInvoiceById(@Param("recordId") Long recordId) throws RepositoryException;

	public void saveRecord(@Param("invoice") Invoice invoice) throws RepositoryException;

	public void updateRecord(@Param("invoice") Invoice invoice) throws RepositoryException;

	public Invoice getInvoiceBySubscriptionId(@Param("subscriptionId") Long subscriptionId) throws RepositoryException;

	public List<Invoice> getInvoicesByCompanyId(@Param("invoiceId") Long invoiceId) throws RepositoryException;

	public List<Invoice> getInvoicesByBillingPeriod(@Param("billingPeriod") String billingPeriod)
			throws RepositoryException;

	public Invoice getInvoiceByCustomerIdCompanyIdBillingPeriod(@Param("customerId") Long customerId,
			@Param("companyId") Long companyId, @Param("billingPeriod") String billingPeriod)
			throws RepositoryException;

	public Long getNextVal();
}
