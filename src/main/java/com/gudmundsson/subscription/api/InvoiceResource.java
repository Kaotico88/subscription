package com.gudmundsson.subscription.api;

import static java.util.Optional.ofNullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gudmundsson.subscription.dto.HealthMessage;
import com.gudmundsson.subscription.dto.ResponseInvoiceDto;
import com.gudmundsson.subscription.service.InvoiceService;
import com.gudmundsson.subscription.service.ResponseInvoiceService;
import com.gudmundsson.subscription.util.AElog;
import com.gudmundsson.subscription.util.AEutil;
import com.gudmundsson.subscription.util.exception.response.custom.CustomRuntimeException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceResource {

	private static final Logger logger = LoggerFactory.getLogger(InvoiceResource.class);

	@Autowired
	private AEutil util;
	
	 @Autowired
	 private ResponseInvoiceService responseInvoiceService;
	
	@GetMapping("/status")
	public ResponseEntity<Object> healthRequest(HttpServletRequest request) throws Exception {

		HealthMessage object;
		HttpHeaders responseHeaders = new HttpHeaders();
		requestLog(request, "X: ");

		object = new HealthMessage("Service is operating normally in Invoice!!");

		responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
		return new ResponseEntity<Object>(object, responseHeaders, HttpStatus.OK);
	}
	
	
	@GetMapping("/{invoiceId}")
	public ResponseEntity<ResponseInvoiceDto> getInvoice(@PathVariable("invoiceId") Long invoiceId,
			HttpServletRequest request) {

		String sessionLogId = System.currentTimeMillis() + ": ";
		ResponseInvoiceDto responseObj = new ResponseInvoiceDto();// este es el objetito
		HttpHeaders responseHeaders = new HttpHeaders();
		requestLog(request, sessionLogId);

		if (invoiceId == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'invoiceId' no es valido");
		}

		responseObj = responseInvoiceService.getInvoiceDetails(ofNullable(invoiceId), sessionLogId);

		if (responseObj == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "No se encontraron datos para la busqueda");
		}

		responseHeaders.set("Custom-Message", "HTTP/1.1 200 Ok");
		return new ResponseEntity<ResponseInvoiceDto>(responseObj, responseHeaders, HttpStatus.ACCEPTED);

	}
	
	
	
	private synchronized void requestLog(HttpServletRequest request, String sessionLogId) {
		AElog.infoX(logger,
				sessionLogId + util.getInetAddressPort() + " <= " + request.getRemoteHost() + " {method:"
						+ request.getMethod() + ", URI:" + request.getRequestURI() + ", query:"
						+ request.getQueryString() + "}");
	}
}
