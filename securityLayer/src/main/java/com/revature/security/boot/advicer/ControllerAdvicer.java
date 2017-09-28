package com.revature.security.boot.advicer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.revature.security.exception.AuthenticationFailsException;
import com.revature.security.exception.ServiceException;
import com.revature.security.exception.UnAuthorizedException;
import com.revature.security.utils.CustomCode;
import com.revature.security.utils.ErrorType;
import com.revature.security.utils.Response;

/**
 * This is advice for all Rest controller
 * 
 * @author MUTHU G.K
 *
 */
@ControllerAdvice
public class ControllerAdvicer extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Response> exception(Exception ex) {
    return new ResponseEntity<>(
        Response.init().setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .setCode(CustomCode.INTERNAL_SERVER_ERROR).setErrorType(ErrorType.SEVERE)
            .setDetailMsg(ex.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AuthenticationFailsException.class)
  public ResponseEntity<Response> authenticationFailsException(AuthenticationFailsException afe) {
    return new ResponseEntity<>(
        Response.init().setCode(afe.getCode()).setErrorType(afe.getErrorType())
            .setMsg(afe.getMsg() == null ? afe.getOrginalMessage() : afe.getMsg())
            .setDetailMsg(afe.getMessage()),
        afe.getCode() == null ? HttpStatus.UNAUTHORIZED : afe.getCode().getStatusCode());
  }

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<Response> serviceException(ServiceException se) {
    return new ResponseEntity<>(
        Response.init().setCode(se.getCode()).setErrorType(se.getErrorType())
            .setMsg(se.getMsg() == null ? se.getOrginalMessage() : se.getMsg())
            .setDetailMsg(se.getMessage()),
        se.getCode() == null ? HttpStatus.INTERNAL_SERVER_ERROR : se.getCode().getStatusCode());
  }

  @ExceptionHandler(UnAuthorizedException.class)
  public ResponseEntity<Response> unAuthorizedException(UnAuthorizedException uae) {
    return new ResponseEntity<>(
        Response.init().setCode(uae.getCode()).setErrorType(uae.getErrorType())
            .setMsg(uae.getMsg() == null ? uae.getOrginalMessage() : uae.getMsg())
            .setDetailMsg(uae.getMessage()),
        uae.getCode() == null ? HttpStatus.UNAUTHORIZED : uae.getCode().getStatusCode());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Response> runtimeException(RuntimeException ex) {
    return new ResponseEntity<>(
        Response.init().setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .setCode(CustomCode.INTERNAL_SERVER_ERROR).setErrorType(ErrorType.SEVERE)
            .setDetailMsg(ex.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
