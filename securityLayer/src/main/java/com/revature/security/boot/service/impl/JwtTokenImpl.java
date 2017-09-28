package com.revature.security.boot.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.security.boot.model.SystemUser;
import com.revature.security.exception.ServiceException;
import com.revature.security.utils.AppConstants;
import com.revature.security.utils.CustomCode;
import com.revature.security.utils.ErrorType;

/**
 * This is JWT token service implementation
 * 
 * @author MUTHU G.K
 *
 */
@Service
public class JwtTokenImpl {

  // ------------------------------------------ Local Constants
  private static final Logger LOG = LogManager.getLogger(JwtTokenImpl.class);

  private static final String ISSUER = "RevaturePro";
  private static final String USER_NAME = "name";
  private static final String USER_ROLE = "role";
  private static final String USER_ORG = "org";

  // ------------------------------------------- Environment Variables

  private final String signingKey;
  private final int minutesToLive;
  private final JWTVerifier verifier;

  // -------------------------------------------- Constructor
  public JwtTokenImpl() throws UnsupportedEncodingException {
    this.signingKey = AppConstants.getEnv("JWT_SIGNING_KEY");
    String minToLive = AppConstants.getEnv("JWT_MINUTES_TO_LIVE");
    this.minutesToLive = minToLive == null ? 0 : Integer.parseInt(minToLive);
    this.verifier = JWT.require(Algorithm.HMAC256(signingKey)).withIssuer(ISSUER).build();
  }

  // -------------------------------------------- Most often methods

  private void throwServiceException(String msg, Throwable throwable) {

    throw new ServiceException(msg, throwable).setMsg(msg).setMsg(msg)
        .setErrorType(ErrorType.SEVERE).setStatus(CustomCode.INTERNAL_SERVER_ERROR);
  }

  private String getUserRoleCode(SystemUser user) {
    return user.getCurrentSystemRole() != null ? user.getCurrentSystemRole().getCode() : "";
  }

  private String getUserOrgId(SystemUser user) {
    boolean isValidEmpObject = user != null && user.getEmployee() != null;
    boolean isValidOrgObject = isValidEmpObject && user.getEmployee().getOrganization() != null;

    return isValidOrgObject && user.getEmployee().getOrganization().getId() != null
        ? user.getEmployee().getOrganization().getId().toString() : "";

  }

  // -------------------------------------------- Logical methods

  /**
   * This is to generate the JWT token
   * 
   * @param user
   * @return
   */
  public String getJwtToken(SystemUser user) {
    if (user == null) {
      throwServiceException("Invalid prams on JWT Token generation", null);
    }

    String token = null;

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, minutesToLive);
    Date expiredOn = calendar.getTime();

    try {
      token = JWT.create().withIssuer(ISSUER) // issuer
          .withJWTId(String.valueOf(user.getId())) // JWT Id
          .withClaim(USER_NAME, user.getUserName()) // User Name
          .withClaim(USER_ROLE, getUserRoleCode(user)) // Role
          .withClaim(USER_ORG, getUserOrgId(user)) // Org Id
          .withExpiresAt(expiredOn) // Expired ON
          .sign(Algorithm.HMAC256(signingKey)); // Symmetric key

    } catch (UnsupportedEncodingException e) {
      LOG.error(e.getMessage(), e);
      throwServiceException("Token Generate fails", e);
    }

    return token;
  }

  /**
   * This is to verify the JWT token, Whether it's valid or not
   * 
   * @param token
   * @return
   */
  public DecodedJWT verifyToken(String token) {
    return verifier.verify(token);
  }

}
