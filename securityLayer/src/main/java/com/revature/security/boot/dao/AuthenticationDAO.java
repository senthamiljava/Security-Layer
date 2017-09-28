package com.revature.security.boot.dao;

import java.util.List;

import com.revature.security.boot.dao.exceptions.DataServiceException;
import com.revature.security.boot.model.SystemUser;

public interface AuthenticationDAO {

  /**
   * This is for get the user object by user name
   * 
   * @param user
   * @return SystemUser
   * @throws DataServiceException
   */
  public SystemUser getUserByName(SystemUser user) throws DataServiceException;

  /**
   * This is for get the user details based on user name and default organization id after login
   * succeed
   * 
   * @param username
   * @param orgId
   * @return SystemUser
   * @throws DataServiceException
   */
  public SystemUser getUserDetailsByNameAndOrgId(String username, Long orgId)
      throws DataServiceException;

  /**
   * This is to update the last login time of the the user
   * 
   * @param updateList
   * @throws DataServiceException
   */
  public void updateUserLastLogin(List<Object> updateList) throws DataServiceException;
}
