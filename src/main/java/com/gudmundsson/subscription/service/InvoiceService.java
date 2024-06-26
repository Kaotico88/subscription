package com.gudmundsson.subscription.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.Invoice;
import com.gudmundsson.subscription.dao.RInvoiceRepository;
import com.gudmundsson.subscription.util.exception.RepositoryException;


@Service
public class InvoiceService {

	@Autowired
	private RInvoiceRepository repository;

	public Invoice getInvoiceById(Optional<Long> id) throws RepositoryException {
		return repository.getInvoiceById(id);
	}

	public Invoice update(Invoice object) throws RepositoryException {
		return repository.update(object);
	}
	
	public List<Invoice> getInvoicesByBillingPeriod(Optional<String> billingPeriod) throws RepositoryException{
		return repository.getInvoicesByBillingPeriod(billingPeriod);
	}

	public Invoice getInvoiceByCustomerIdCompanyIdBillingPeriod(Optional<Long> customerId, Optional<Long> companyId,
			Optional<String> billingPeriod) throws RepositoryException {
		return repository.getInvoiceByCustomerIdCompanyIdBillingPeriod(customerId, companyId, billingPeriod);
	}

}
