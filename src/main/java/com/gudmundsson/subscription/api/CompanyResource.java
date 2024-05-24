package com.gudmundsson.subscription.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.dto.CompanyDto;
import com.gudmundsson.subscription.dto.HealthMessage;
import com.gudmundsson.subscription.service.CompanyService;
import com.gudmundsson.subscription.util.AElog;
import com.gudmundsson.subscription.util.AEutil;
import com.gudmundsson.subscription.util.exception.response.custom.CustomRuntimeException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/companies/register")
public class CompanyResource {

	private static final Logger logger = LoggerFactory.getLogger(CompanyResource.class);

	@Autowired
	private AEutil util;

	@Autowired
	private CompanyService companyService;

	@GetMapping("/status")
	public ResponseEntity<HealthMessage> healthRequest(HttpServletRequest request) throws Exception {

		HealthMessage message;
		HttpHeaders responseHeaders = new HttpHeaders();
		requestLog(request, "X: ");

		message = new HealthMessage("Service is operating normally at company!!");

		responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
		return new ResponseEntity<HealthMessage>(message, responseHeaders, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Company> save(@RequestBody CompanyDto companyDto, HttpServletRequest request) {
		String sessionLogId = System.currentTimeMillis() + ": ";
		Company responseObj = new Company();// este es el objetito
		HttpHeaders responseHeaders = new HttpHeaders();
		requestLog(request, sessionLogId);

		if (companyDto == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El objeto que se desea registrar es nulo.");
		}

		companyDto.copyToCore(responseObj);
		responseObj = companyService.save(responseObj);

		if (responseObj == null || responseObj.getCompanyId() == null) {
			throw new CustomRuntimeException(HttpStatus.CONFLICT, "La compania no se pudo registrar.");
		}

		responseHeaders.set("Custom-Message", "HTTP/1.1 201 CREATED");
		return new ResponseEntity<Company>(responseObj, responseHeaders, HttpStatus.CREATED);
	}

	private synchronized void requestLog(HttpServletRequest request, String sessionLogId) {
		AElog.infoX(logger,
				sessionLogId + util.getInetAddressPort() + " <= " + request.getRemoteHost() + " {method:"
						+ request.getMethod() + ", URI:" + request.getRequestURI() + ", query:"
						+ request.getQueryString() + "}");
	}
}
