package com.revature.security.boot.dao.exceptions;

public class DataInsertFailedException extends DataServiceException {

  private static final long serialVersionUID = 1L;

  public DataInsertFailedException() {
    super();
  }

  public DataInsertFailedException(String message) {
    super(message);
  }

  public DataInsertFailedException(Throwable exception) {
    super(exception);
  }

  public DataInsertFailedException(String message, Throwable exception) {
    super(message, exception);
  }
}
