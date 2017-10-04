package com.revature.security.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.security.boot.service.TokenService;
import com.revature.security.utils.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * This controller for the token verification
 * 
 * @author MUTHU G.K
 *
 */
@RestController
@RequestMapping("api/token/")
@Api(value="chech", description="Token Verification")
public class TokenVerificationController {

	@Autowired
	private TokenService tokenService;

	public TokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@GetMapping("verify")
	@ApiOperation(value = "Verify Token")
	public ResponseEntity<Response> doVerification(@RequestHeader("authToken") String token) {
		getTokenService().verifyToken(token);
		return ResponseEntity.ok(Response.init().success().setData(token).setMsg("Authorized user"));

	}

}
