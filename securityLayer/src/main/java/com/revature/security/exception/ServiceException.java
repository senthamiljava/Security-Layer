package com.revature.security.exception;

import com.revature.security.utils.CustomCode;
import com.revature.security.utils.ErrorType;

/**
 * This exception for the service exception
 * 
 * @author MUTHU G.K
 *
 */
public class ServiceException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private CustomCode code;
  private String msg;
  private String detailsMsg;
  private ErrorType errorType;

  /**
   * No argument constructor
   */
  public ServiceException() {
    super();
  }

  /**
   * This is for set the message into the exception
   * 
   * @param msg
   */
  public ServiceException(String msg) {
    super(msg);
  }

  /**
   * This is for set the message and exception
   * 
   * @param msg
   * @param t
   */
  public ServiceException(String msg, Throwable t) {
    super(msg, t);
  }

  /**
   * This for set the exception
   * 
   * @param t
   */
  public ServiceException(Throwable t) {
    super(t);
  }

  // --------------------------------------- Getter

  public CustomCode getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public String getDetailsMsg() {
    return detailsMsg;
  }

  public ErrorType getErrorType() {
    return errorType;
  }

  // --------------------------------------- Setter
  public ServiceException setStatus(CustomCode code) {
    this.code = code;
    return this;
  }

  public ServiceException setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  public ServiceException setDetailsMsg(String detailsMsg) {
    this.detailsMsg = detailsMsg;
    return this;
  }

  public ServiceException setErrorType(ErrorType errorType) {
    this.errorType = errorType;
    return this;
  }

  // --------------------------------- custom override
  public String getOrginalMessage() {
    return super.getMessage();
  }

  @Override
  public String getMessage() {
    return new StringBuilder().append(super.getMessage()).append(" (Error Type: ").append(errorType)
        .append("; Custome Code: ").append(code).append("; Message : ").append(msg)
        .append("; Detail Message : ").append(detailsMsg).append(")").toString();
  }

}
