package com.revature.security.boot.service;

import com.revature.security.boot.model.SystemUser;

/**
 * This is for handle the security token
 * 
 * @author MUTHU G.K
 *
 */
public interface TokenService {

  /**
   * THis is to get the token
   * 
   * @param user
   * @return
   */
  String doGetToken(SystemUser user);

  /**
   * This is for verify the token
   * 
   * @param token
   * @return
   */
  boolean verifyToken(String token);

}
