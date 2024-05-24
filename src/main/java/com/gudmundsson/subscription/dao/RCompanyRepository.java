package com.gudmundsson.subscription.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Repository
public class RCompanyRepository {

	@Autowired
	private MCompanyMapper mapper;

	public Company getCompanyById(Optional<Long> id) throws RepositoryException {
		return mapper.getCompanyById(id.orElse(null));
	}

	public Company save(Company object) throws RepositoryException {
		long id = nextVal();
		object.setCompanyId(id);
		mapper.saveRecord(object);
		return object;
	}

	public List<Company> getCompaniesByInvoiceId(Optional<Long> invoiceId) throws RepositoryException {
		return mapper.getCompaniesByInvoiceId(invoiceId.orElse(null));
	}

	public Long nextVal() {
		return mapper.getNextVal();
	}

}
