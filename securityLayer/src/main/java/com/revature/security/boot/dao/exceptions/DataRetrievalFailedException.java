package com.revature.security.boot.dao.exceptions;

public class DataRetrievalFailedException extends DataServiceException {

  private static final long serialVersionUID = 1L;

  public DataRetrievalFailedException() {
    super();
  }

  public DataRetrievalFailedException(String message) {
    super(message);
  }

  public DataRetrievalFailedException(Throwable exception) {
    super(exception);
  }

  public DataRetrievalFailedException(String message, Throwable exception) {
    super(message, exception);
  }
}
