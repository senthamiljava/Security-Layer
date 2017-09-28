package com.revature.security.boot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This is for check the server status, whether server is up status or not
 * 
 * @author MUTHU G.K
 *
 */
@RestController
@RequestMapping(path = "check/")
@Api(value="chech", description="Check Server Status")
public class CheckServerStatus {

  private static final Logger LOG = LogManager.getLogger(CheckServerStatus.class);

  @ApiOperation(value = "Get Status")
  @GetMapping(path = "server-up")
  public ResponseEntity<String> getServerStatus() {

    LOG.info("Getting into server status checking method ");
    return ResponseEntity.ok("The security server is being up only");

  }

}
