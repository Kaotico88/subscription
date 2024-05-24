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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gudmundsson.subscription.core.Customer;
import com.gudmundsson.subscription.core.ItemService;
import com.gudmundsson.subscription.core.Subscription;
import com.gudmundsson.subscription.dto.HealthMessage;
import com.gudmundsson.subscription.dto.SubscriptionDto;
import com.gudmundsson.subscription.service.CustomerService;
import com.gudmundsson.subscription.service.ItemServiceService;
import com.gudmundsson.subscription.service.SubscriptionService;
import com.gudmundsson.subscription.util.AElog;
import com.gudmundsson.subscription.util.AEutil;
import com.gudmundsson.subscription.util.exception.response.custom.CustomRuntimeException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionResource {

	private static final Logger logger = LoggerFactory.getLogger(CompanyResource.class);

	@Autowired
	private AEutil util;

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ItemServiceService itemServiceService;

	@GetMapping("/status")
	public ResponseEntity<HealthMessage> healthRequest(HttpServletRequest request) throws Exception {

		HealthMessage message;
		HttpHeaders responseHeaders = new HttpHeaders();
		requestLog(request, "X: ");

		message = new HealthMessage("Service is operating normally!! at subscription");

		responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
		return new ResponseEntity<HealthMessage>(message, responseHeaders, HttpStatus.OK);
	}

	@PostMapping("/customer/{customerId}/itemService/{itemServiceId}/activate")
	public ResponseEntity<Subscription> register(@PathVariable("customerId") Long customerId,
			@PathVariable("itemServiceId") Long itemServiceId, @RequestBody SubscriptionDto subscriptionDto,
			HttpServletRequest request) {
		String sessionLogId = System.currentTimeMillis() + ": ";

		Subscription responseObj = new Subscription();// este es el objetito
		Customer customer = new Customer();
		ItemService itemService = new ItemService();

		HttpHeaders responseHeaders = new HttpHeaders();

		requestLog(request, sessionLogId);

		if (customerId == null || customerId <= 0) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'cusmtomerId' no es valido.");
		}

		if (itemServiceId == null || itemServiceId <= 0) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'itemServiceId' no es valido.");
		}

		if (subscriptionDto == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El objecto que desea registrar es nulo.");
		}

		customer = customerService.getCustomerById(ofNullable(customerId));
		if (customer == null || customer.getCustomerId() == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El 'cliente' no esta registrado.");
		}

		itemService = itemServiceService.getItemServiceById(ofNullable(itemServiceId));
		if (itemService == null || itemService.getItemServiceId() == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El 'itemService' no esta registrado.");
		}

		subscriptionDto.setCustomerId(customerId);
		subscriptionDto.setItemServiceId(itemServiceId);
		subscriptionDto.copyToCore(responseObj);

		responseObj = subscriptionService.save(responseObj);

		if (responseObj == null || responseObj.getSubscriptionId() == null
				|| responseObj.getCustomer().getCustomerId() == null
				|| responseObj.getItemService().getItemServiceId() == null) {
			throw new CustomRuntimeException(HttpStatus.CONFLICT, 400, "La subscripcion no se pudo registrar.");
		}

		responseHeaders.set("Custom-Message", "HTTP/1.1 201 CREATED");
		return new ResponseEntity<Subscription>(responseObj, responseHeaders, HttpStatus.CREATED);
	}

//Endpint para actualizar es estado del una subcription	
	@PutMapping("/{subscriptionId}")
	public ResponseEntity<Subscription> unsubscribe(@PathVariable("subscriptionId") Long subscriptionId,
			@RequestParam(name = "state") Boolean state, @RequestParam(name = "hoursUsed") Double hoursUsed,
			HttpServletRequest request) {
		String sessionLogId = System.currentTimeMillis() + ": ";
		Subscription responseObj = new Subscription();// este es el objetito
		HttpHeaders responseHeaders = new HttpHeaders();

		requestLog(request, sessionLogId);

		if (subscriptionId == null || subscriptionId <= 0) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400,
					"El parametro 'subscriptionId' no es valido.");
		}

		if (state == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'state' no es valido.");
		}

		responseObj = subscriptionService.getSubscriptionById(ofNullable(subscriptionId));

		if (responseObj == null || responseObj.getSubscriptionId() == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "La  'Subscripcion' no esta registrado.");
		}

		responseObj.setState(state);
		responseObj.setHoursUsed(hoursUsed);
		responseObj = subscriptionService.update(responseObj);

		if (responseObj == null || responseObj.getSubscriptionId() == null
				|| responseObj.getCustomer().getCustomerId() == null
				|| responseObj.getItemService().getItemServiceId() == null) {
			throw new CustomRuntimeException(HttpStatus.CONFLICT, 400, "La subscripcion no se pudo registrar.");
		}

		responseHeaders.set("Custom-Message", "HTTP/1.1 201 CREATED");
		return new ResponseEntity<Subscription>(responseObj, responseHeaders, HttpStatus.CREATED);
	}

	private synchronized void requestLog(HttpServletRequest request, String sessionLogId) {
		AElog.infoX(logger,
				sessionLogId + util.getInetAddressPort() + " <= " + request.getRemoteHost() + " {method:"
						+ request.getMethod() + ", URI:" + request.getRequestURI() + ", query:"
						+ request.getQueryString() + "}");
	}
}
