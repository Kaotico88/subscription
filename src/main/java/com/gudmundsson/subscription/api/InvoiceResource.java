package com.gudmundsson.subscription.api;

import static java.util.Optional.ofNullable;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gudmundsson.subscription.dto.HealthMessage;
import com.gudmundsson.subscription.dto.ResponseInvoiceDto;
//import com.gudmundsson.subscription.service.InvoiceService;
import com.gudmundsson.subscription.service.ResponseInvoiceService;
import com.gudmundsson.subscription.util.AElog;
import com.gudmundsson.subscription.util.AEutil;
import com.gudmundsson.subscription.util.exception.response.custom.CustomRuntimeException;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceResource {

	private static final Logger logger = LoggerFactory.getLogger(InvoiceResource.class);

	@Autowired
	private AEutil util;

	@Autowired
	private ResponseInvoiceService responseInvoiceService;

//	@Autowired
//	private InvoiceService invoiceService;

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
	public ResponseEntity<ResponseInvoiceDto> getInvoiceA(@PathVariable("invoiceId") Long invoiceId,
			HttpServletRequest request) {

		String sessionLogId = System.currentTimeMillis() + ": ";
		ResponseInvoiceDto responseObj = new ResponseInvoiceDto();// este es el objetito
		HttpHeaders responseHeaders = new HttpHeaders();
		requestLog(request, sessionLogId);

		if (invoiceId == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'invoiceId' no es valido");
		}

		responseObj = responseInvoiceService.getInvoiceDetailsA(ofNullable(invoiceId), sessionLogId);

		if (responseObj == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "No se encontraron datos para la busqueda");
		}

		responseHeaders.set("Custom-Message", "HTTP/1.1 200 Ok");
		return new ResponseEntity<ResponseInvoiceDto>(responseObj, responseHeaders, HttpStatus.ACCEPTED);

	}

	@GetMapping("/{customerId}/{companyId}/{billingPeriod}")
	public ResponseEntity<ResponseInvoiceDto> getInvoiceB(@PathVariable("customerId") Long customerId,
			@PathVariable("companyId") Long companyId, @PathVariable("billingPeriod") String billingPeriod,
			HttpServletRequest request) {

		String sessionLogId = System.currentTimeMillis() + ": ";
		ResponseInvoiceDto responseObj = new ResponseInvoiceDto();// este es el objetito
		HttpHeaders responseHeaders = new HttpHeaders();
		requestLog(request, sessionLogId);

		if (customerId == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'customerId' no es valido");
		}

		if (companyId == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro '(companyId' no es valido");
		}

		if (billingPeriod == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'billingPeriod' no es valido");
		}

		responseObj = responseInvoiceService.getInvoiceDetailsB(ofNullable(customerId), ofNullable(companyId),
				ofNullable(billingPeriod), sessionLogId);

		if (responseObj == null || responseObj.getData().getCustomer() == null
				|| responseObj.getData().getCompanies() == null
				|| responseObj.getData().getInvoice().getBillingPeriod() == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "No se encontraron datos para la busqueda");
		}

		responseHeaders.set("Custom-Message", "HTTP/1.1 200 Ok");
		return new ResponseEntity<ResponseInvoiceDto>(responseObj, responseHeaders, HttpStatus.ACCEPTED);

	}
	
	@GetMapping("/pdf/generate/{invoiceId}")
	public void generatePDF(@PathVariable("invoiceId") Long invoiceId ,HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd:hh:mm");
		String currenteDateTime = dateFormater.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename=pdf_" + currenteDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		
		this.responseInvoiceService.exportA(ofNullable(invoiceId),response);
		
	}
	
//	*** Aca me quede me falta la @GetMapping("/downloadPDF") del chatGPT de google
	@PostMapping("/convertToPDF")
    public ResponseEntity<String> convertToPDF(@RequestParam(name = "customerId") Long customerId, 
    		@RequestParam(name = "companyId") Long companyId, @RequestParam(name = "billingPeriod") String billingPeriod,
    		HttpServletResponse response) throws DocumentException, IOException {
        if (responseInvoiceService.pdfExists(customerId, customerId, billingPeriod)) {
            byte[] pdfBytes = responseInvoiceService.getPDF(customerId, customerId, billingPeriod);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", customerId + companyId + billingPeriod +".pdf");
            return new ResponseEntity<>(new String(pdfBytes), headers, HttpStatus.OK);
        } else {
        	responseInvoiceService.exportB(ofNullable(customerId), ofNullable(companyId), ofNullable(billingPeriod), response);
            return new ResponseEntity<>("Generating invoice, please try again later.", HttpStatus.ACCEPTED);
        }
    }

	

	private synchronized void requestLog(HttpServletRequest request, String sessionLogId) {
		AElog.infoX(logger,
				sessionLogId + util.getInetAddressPort() + " <= " + request.getRemoteHost() + " {method:"
						+ request.getMethod() + ", URI:" + request.getRequestURI() + ", query:"
						+ request.getQueryString() + "}");
	}
	
	
}
