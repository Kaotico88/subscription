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

import com.gudmundsson.subscription.core.User;
import com.gudmundsson.subscription.dto.HealthMessage;
import com.gudmundsson.subscription.dto.UserDto;
import com.gudmundsson.subscription.service.UserService;
//import com.gudmundsson.subscription.service.UserService;
import com.gudmundsson.subscription.util.AElog;
import com.gudmundsson.subscription.util.AEutil;
import com.gudmundsson.subscription.util.exception.response.custom.CustomRuntimeException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/users")
public class UserResource {

	private static final Logger logger = LoggerFactory.getLogger(UserResource.class);
	
	@Autowired
	private AEutil util;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/status")
	public ResponseEntity<HealthMessage> healthRequest(HttpServletRequest request) throws Exception {

		HealthMessage message;
		HttpHeaders responseHeaders = new HttpHeaders();
		requestLog(request, "X: ");

		message = new HealthMessage("Service is operating normally!!");

		responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
		return new ResponseEntity<HealthMessage>(message, responseHeaders, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody UserDto userDto, HttpServletRequest request){
		String sessionLogId = System.currentTimeMillis() + ": ";
		User responseObj = new User();// este es el objetito
		HttpHeaders responseHeaders = new HttpHeaders();
		requestLog(request, sessionLogId);
		
		if(userDto == null) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El objeto que se desea registrar es nulo.");
		}
		
		userDto.copyToCore(responseObj);
		responseObj = userService.save(responseObj);
		
		if(responseObj == null || responseObj.getUserId() == null) {
			throw new CustomRuntimeException(HttpStatus.CONFLICT, "El usuario no se pudo registrar.");
		}
		
		responseHeaders.set("Custom-Message", "HTTP/1.1 201 CREATED");
        return new ResponseEntity<User>(responseObj, responseHeaders, HttpStatus.CREATED);
	}
	
	
	private synchronized void requestLog(HttpServletRequest request, String sessionLogId) {
		AElog.infoX(logger,
				sessionLogId + util.getInetAddressPort() + " <= " + request.getRemoteHost() + " {method:"
						+ request.getMethod() + ", URI:" + request.getRequestURI() + ", query:"
						+ request.getQueryString() + "}");
	}
	
}
