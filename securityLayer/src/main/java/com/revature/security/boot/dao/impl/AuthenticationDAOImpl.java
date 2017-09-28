package com.revature.security.boot.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.security.boot.dao.AuthenticationDAO;
import com.revature.security.boot.dao.exceptions.DataRetrievalFailedException;
import com.revature.security.boot.dao.exceptions.DataServiceException;
import com.revature.security.boot.db.DataModifier;
import com.revature.security.boot.db.DataRetriever;
import com.revature.security.boot.db.QueryParameter;
import com.revature.security.boot.db.exception.DataAccessException;
import com.revature.security.boot.model.OrgSystemUserRole;
import com.revature.security.boot.model.SystemUser;

/**
 * This is for user authentication data layer implementation
 * 
 * @author MUTHU G.K
 *
 */
@Repository
public class AuthenticationDAOImpl implements AuthenticationDAO {

  private static final Logger LOG = LogManager.getLogger(AuthenticationDAOImpl.class);
  private static final String USERNAME = "userName";

  // ------------------------------------- DI variables
  @Autowired
  private DataRetriever dataRetriever;

  @Autowired
  private DataModifier dataModifier;

  // ------------------------------------- DI Getter Setters
  public DataRetriever getDataRetriever() {
    return dataRetriever;
  }

  public void setDataRetriever(DataRetriever dataRetriever) {
    this.dataRetriever = dataRetriever;
  }

  public DataModifier getDataModifier() {
    return dataModifier;
  }

  public void setDataModifier(DataModifier dataModifier) {
    this.dataModifier = dataModifier;
  }

  // ----------------------------------------- Most often methods
  private SystemUser createSystemUser(List<OrgSystemUserRole> systemUserWithRoleList) {
    SystemUser sysUser = null;
    int i = 0;
    for (OrgSystemUserRole userRoll : systemUserWithRoleList) {
      if (i == 0) {
        sysUser = userRoll.getOrgSystemUser().getSystemUser();
        sysUser.setCurrentSystemRole(userRoll.getSeedSystemRole());
      }
      if (sysUser.getSystemUserRoles() == null) {
        sysUser.setSystemUserRoles(new ArrayList<>());
      }

      sysUser.getSystemUserRoles().add(userRoll.getSeedSystemRole());
      i++;
    }

    return sysUser;
  }

  // ------------------------------------------- Querying Methods
  /**
   * This is for get the user object by user name
   * 
   * @param user
   * @return SystemUser
   */
  @Override
  public SystemUser getUserByName(SystemUser user) throws DataServiceException {

    SystemUser systemUser = null;
    try {
      LOG.info("Create query to get the user by name (input detais :: userName {})",
          user.getUserName());

      StringBuilder queryString = new StringBuilder("From SystemUser where userName=:userName");
      List<QueryParameter<?>> queryParameters = new ArrayList<>();
      queryParameters.add(new QueryParameter<>(USERNAME, user.getUserName()));
      systemUser = getDataRetriever().retrieveObjectByHQL(queryString.toString(), queryParameters);

      if (systemUser == null || systemUser.getId() == null) {
        LOG.info("No user found for the userName = {}", user.getUserName());
      } else {
        LOG.info(" User data retrieved successfully based on the userName ={}", user.getUserName());
      }
    } catch (DataAccessException e) {
      LOG.error("User retrieval fails based on userName = {} :: errorMsg = {} ", user.getUserName(),
          e.getMessage(), e);

      throw new DataRetrievalFailedException("User retrieval fails", e);
    }
    return systemUser;

  }

  @Override
  public SystemUser getUserDetailsByNameAndOrgId(String userName, Long orgId)
      throws DataServiceException {

    SystemUser systemUser = null;
    try {
      LOG.info(
          "Create query to get the user with roles by name and org id (input detais :: userName = {} , orgId = {})",
          userName, orgId);

      StringBuilder query = new StringBuilder(
          "From OrgSystemUserRole ur left join fetch ur.orgSystemUser osu join fetch ").append(
              " osu.organization left join fetch osu.systemUser su left join fetch su.employee e left join fetch e.organization ")
              .append(" where osu.systemUser.userName=:userName and osu.organization.id=:orgId  ")
              .append(" order by ur.seedSystemRole.hierarchy ");

      List<QueryParameter<?>> queryParam = new ArrayList<>();
      queryParam.add(new QueryParameter<>(USERNAME, userName));
      queryParam.add(new QueryParameter<>("orgId", orgId));
      List<OrgSystemUserRole> systemUserAndRoles =
          getDataRetriever().retrieveByHQL(query.toString(), queryParam);

      if (systemUserAndRoles != null && !systemUserAndRoles.isEmpty()) {
        systemUser = createSystemUser(systemUserAndRoles);

      } else {

        query = new StringBuilder(" From SystemUser su where su.userName=:userName ");
        List<QueryParameter<?>> queryParameter = new ArrayList<>();
        queryParameter.add(new QueryParameter<>(USERNAME, userName));
        systemUser = getDataRetriever().retrieveObjectByHQL(query.toString(), queryParameter);

      }
      LOG.info(" User data with roles retrieved successfully based on the userName ={}, orgId ={}",
          userName, orgId);
    } catch (DataAccessException e) {
      LOG.error("User retrieval fails based on userName = {}, orgId ={} :: errorMsg = {} ",
          userName, orgId, e.getMessage(), e);

      throw new DataRetrievalFailedException("User details retrieval fails", e);
    }
    return systemUser;

  }

  @Override
  public void updateUserLastLogin(List<Object> updateList) throws DataServiceException {

    try {
      LOG.info("Create query for updating last login details.");
      getDataModifier().updateBulk(updateList);
      LOG.debug("Last login details updated successfully.");
    } catch (DataAccessException dataAccessException) {
      LOG.error("Last login details updation failed", dataAccessException);
      throw new DataRetrievalFailedException("Last login details update fails",
          dataAccessException);
    }

  }

}
