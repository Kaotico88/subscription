package com.gudmundsson.subscription.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gudmundsson.subscription.core.Invoice;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Repository
public class RInvoiceRepository {

	@Autowired
	private MInvoiceMapper mapper;

	public Invoice getInvoiceById(Optional<Long> id) throws RepositoryException {
		return mapper.getInvoiceById(id.orElse(null));
	}

	public Invoice save(Invoice object) throws RepositoryException {
		long id = nextVal();
		object.setInvoiceId(id);
		mapper.saveRecord(object);
		return object;
	}

	public Invoice update(Invoice object) throws RepositoryException {
		mapper.updateRecord(object);
		return object;
	}

	public Invoice getInvoiceBySubscriptionId(Optional<Long> id) throws RepositoryException {
		return mapper.getInvoiceBySubscriptionId(id.orElse(null));
	}

	public List<Invoice> getInvoicesByCompanyId(Optional<Long> id) throws RepositoryException {
		return mapper.getInvoicesByCompanyId(id.orElse(null));
	}

	public List<Invoice> getInvoicesByBillingPeriod(Optional<String> billingPeriod) throws RepositoryException {
		return mapper.getInvoicesByBillingPeriod(billingPeriod.orElse(null));
	}

	public Invoice getInvoiceByCustomerIdCompanyIdBillingPeriod(Optional<Long> customerId, Optional<Long> companyId,
			Optional<String> billingPeriod) throws RepositoryException {
		return mapper.getInvoiceByCustomerIdCompanyIdBillingPeriod(customerId.orElse(null), companyId.orElse(null),
				billingPeriod.orElse(null));
	}

	public Long nextVal() {
		return mapper.getNextVal();
	}
}
