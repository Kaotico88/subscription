package com.gudmundsson.subscription.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.dao.RInvoiceRepository;
import com.gudmundsson.subscription.dto.ResponseInvoiceDto;

@Service
public class ResponseInvoiceService {

	@Autowired
	private RInvoiceRepository repository;
	
	@Autowired
	private InvoiceService invoiceService;
	
	public ResponseInvoiceDto getInvoiceDetails(Optional<Long> id) {
		Invoice invoice = invoiceService.
	}
}
