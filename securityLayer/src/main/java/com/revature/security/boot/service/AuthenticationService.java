package com.revature.security.boot.service;



import com.revature.security.boot.model.SystemUser;

public interface AuthenticationService {

  /**
   * This is for authenticate the user while try to login
   * 
   * @param user
   * @return
   */
  public SystemUser doAuthentication(SystemUser user);

  /**
   * This is for update the last login details
   * 
   * @param user
   * 
   */
  public void doUpdateUserLastLogin(SystemUser user);
}
