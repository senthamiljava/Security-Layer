package com.revature.security.utils;

/**
 * This is a response body for all rest controller
 * 
 * @author MUTHU G.K
 *
 */
public class Response {

  private String code;
  private String msg;
  private String errorType;
  private String detailMsg;
  private Object data;

  // --------------------------- Getter
  public String getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public Object getData() {
    return data;
  }

  public String getDetailMsg() {
    return detailMsg;
  }

  public String getErrorType() {
    return errorType;
  }

  // --------------------------- Setter
  public Response setCode(CustomCode code) {
    this.code = code.getCode();
    return this;
  }

  public Response setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  public Response setData(Object data) {
    this.data = data;
    return this;
  }

  public Response setDetailMsg(String detailMsg) {
    this.detailMsg = detailMsg;
    return this;
  }

  public Response setErrorType(ErrorType errorType) {
    this.errorType = errorType == null ? null : errorType.toString();
    return this;
  }

  // ---------------------- initializer
  public static Response init() {
    return new Response();
  }

  public Response success() {
    return setCode(CustomCode.SUCCESS);
  }

}
