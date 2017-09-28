package com.revature.security.boot.dao.exceptions;

public class DataDeleteFailedException extends DataServiceException {

  private static final long serialVersionUID = 1L;

  public DataDeleteFailedException() {
    super();
  }

  public DataDeleteFailedException(String message) {
    super(message);
  }

  public DataDeleteFailedException(Throwable exception) {
    super(exception);
  }

  public DataDeleteFailedException(String message, Throwable exception) {
    super(message, exception);
  }
}
