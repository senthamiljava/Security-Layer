package com.revature.security.boot.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.revature.security.boot.model.SystemUser;
import com.revature.security.boot.service.TokenService;
import com.revature.security.exception.AuthenticationFailsException;
import com.revature.security.exception.ServiceException;
import com.revature.security.utils.CustomCode;
import com.revature.security.utils.ErrorType;

/**
 * This is token service to issue and verify the security token
 * 
 * @author MUTHU G.K
 *
 */
@Service
public class TokenServiceImpl implements TokenService {

  private static final Logger LOG = LogManager.getLogger(TokenServiceImpl.class);

  @Autowired
  private JwtTokenImpl jwtTokenImpl;

  public JwtTokenImpl getJwtTokenImpl() {
    return jwtTokenImpl;
  }

  public void setJwtTokenImpl(JwtTokenImpl jwtTokenImpl) {
    this.jwtTokenImpl = jwtTokenImpl;
  }

  // -------------------------------------------- Most often methods
  private void throwAuthenticationException(String msg) {

    throw new AuthenticationFailsException(msg).setMsg(msg).setErrorType(ErrorType.CLIENT)
        .setStatus(CustomCode.UNAUTHORIZED_USER);
  }

  private void throwServiceException(String msg, Throwable throwable) {

    throw new ServiceException(msg, throwable).setMsg(msg).setMsg(msg)
        .setErrorType(ErrorType.SEVERE).setStatus(CustomCode.INTERNAL_SERVER_ERROR);
  }

  // ------------------------------------------ Logic methods
  @Override
  public String doGetToken(SystemUser user) {
    if (user == null || user.getUserName() == null) {
      throwServiceException("Invalid prams on Token generation", null);
    }

    LOG.info("Getting into token generate method for the user = {} ", user.getUserName());
    String token = getJwtTokenImpl().getJwtToken(user);
    LOG.info("Token generated successfully for user = {}, token = {} ", user.getUserName(), token);
    return token;
  }

  @Override
  public boolean verifyToken(String token) {
    LOG.info("Getting into token verification method for the token = {} ", token);

    try {
      getJwtTokenImpl().verifyToken(token);
    } catch (JWTVerificationException e) {
      LOG.error(e.getMessage(), e);
      throwAuthenticationException(ServiceMessages.getMsg("err.invalid.token"));
    }
    LOG.info("Token Verified successfully :: token = {} ", token);
    return true;
  }

}
