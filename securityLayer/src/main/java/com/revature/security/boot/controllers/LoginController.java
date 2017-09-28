package com.revature.security.boot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.security.boot.model.SystemUser;
import com.revature.security.boot.service.AuthenticationService;
import com.revature.security.utils.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This is for validate the user login
 * 
 * @author MUTHU G.K
 *
 */

@RestController
@RequestMapping("api/login")
@Api(value="login", description="Login")
public class LoginController {

	private static final Logger LOG = LogManager.getLogger(LoginController.class);

	@Autowired
	private AuthenticationService authenticationService;

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping
	@ApiOperation(value = "Login User")
	public ResponseEntity<Response> doAuthenticate(@RequestBody SystemUser user) {

		LOG.info("Getting into user login Controller :: userName = {} ", user.getUserName());
		SystemUser systemUser = getAuthenticationService().doAuthentication(user);

		return ResponseEntity.ok(Response.init().success().setData(systemUser));

	}

}
