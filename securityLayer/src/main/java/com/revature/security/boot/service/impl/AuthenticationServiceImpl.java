package com.revature.security.boot.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.security.boot.dao.AuthenticationDAO;
import com.revature.security.boot.dao.exceptions.DataServiceException;
import com.revature.security.boot.model.Employee;
import com.revature.security.boot.model.SystemUser;
import com.revature.security.boot.service.AuthenticationService;
import com.revature.security.boot.service.TokenService;
import com.revature.security.exception.AuthenticationFailsException;
import com.revature.security.exception.ServiceException;
import com.revature.security.utils.AppConstants;
import com.revature.security.utils.CustomCode;
import com.revature.security.utils.ErrorType;
import com.revature.security.utils.PasswordEncryptionUtils;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger LOG = LogManager.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	private AuthenticationDAO authenticationDAO;

	@Autowired
	private TokenService tokenService;

	public AuthenticationDAO getAuthenticationDAO() {
		return authenticationDAO;
	}

	public void setAuthenticationDAO(AuthenticationDAO authenticationDAO) {
		this.authenticationDAO = authenticationDAO;
	}

	public TokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	// ----------------------------------------- most often methods

	private void throwAuthenticationException(String msg) {

		throw new AuthenticationFailsException(msg).setMsg(msg).setErrorType(ErrorType.CLIENT)
				.setStatus(CustomCode.UNAUTHORIZED_USER);
	}

	private void throwServiceException(String msg, Throwable throwable) {

		throw new ServiceException(msg, throwable).setMsg(msg).setMsg(msg).setErrorType(ErrorType.SEVERE)
				.setStatus(CustomCode.INTERNAL_SERVER_ERROR);
	}
	// ------------------------------------------ Logical Methods

	/**
	 * This is for check, whether user has the authentication or not
	 * 
	 * @param user
	 * @return SystemUser
	 * 
	 */
	@Override
	public SystemUser doAuthentication(SystemUser user) {

		SystemUser athenticateUser = null;
		try {
			SystemUser systemUser = getAuthenticationDAO().getUserByName(user);

			if (systemUser == null || systemUser.getId() == null) {
				throwAuthenticationException(ServiceMessages.getMsg("err.incorrect.user.name.psw"));
			}
			systemUser.setPassword(user.getPassword());

			athenticateUser = getAuthenticationDAO().getUserDetailsByNameAndOrgId(systemUser.getUserName(),
					systemUser.getEmployee().getOrganization().getId());
			if (systemUser.getCurrentLoginTime() != null) {
				systemUser.setPreviousLoginTime(systemUser.getCurrentLoginTime());
			}

			authenticateTheUser(athenticateUser, systemUser);

			String token = getTokenService().doGetToken(athenticateUser);
			athenticateUser.setLoginToken(token);

		} catch (DataServiceException e) {
			LOG.error(e.getMessage(), e);
			throwServiceException(ServiceMessages.getMsg("err.authenticate"), e);
		}

		return athenticateUser;
	}

	private void authenticateTheUser(SystemUser athenticateUser, SystemUser systemUser) {

		boolean isInactiveUser = athenticateUser.getIsActive() == null || !athenticateUser.getIsActive();
		boolean isInactiveEmployee = athenticateUser.getEmployee() != null
				&& (athenticateUser.getEmployee().getIsActive() == null
						|| !athenticateUser.getEmployee().getIsActive());

		if (isInactiveUser || isInactiveEmployee) {
			throwAuthenticationException(ServiceMessages.getMsg("err.in.active.user"));

		} else if (!PasswordEncryptionUtils.getInstance().authenticate(systemUser.getPassword(),
				athenticateUser.getPasswordEncrypt(), athenticateUser.getPasswordSalt())) {
			throwAuthenticationException(ServiceMessages.getMsg("err.incorrect.user.name.psw"));

		} else if (!"SR000".equals(athenticateUser.getCurrentSystemRole().getCode())
				&& (athenticateUser.getEmployee().getOrganization() == null
						|| !athenticateUser.getEmployee().getOrganization().getIsActive())) {
			throwAuthenticationException(ServiceMessages.getMsg("err.inactive.organization"));

		} else {
			athenticateUser.setTimeZone(systemUser.getTimeZone());
			doUpdateUserLastLogin(athenticateUser);
		}
	}

	@Override
	public void doUpdateUserLastLogin(SystemUser systemUser) {

		if (systemUser != null && systemUser.getId() != null) {
			try {
				List<Object> updateList = new ArrayList<>();

				boolean isEmpModified = false;
				Employee employee = systemUser.getEmployee();
				if (BooleanUtils.isFalse(employee.getIsVerified())) {
					employee.setIsVerified(true);
					isEmpModified = true;
				}
				if (!StringUtils.isNotBlank(employee.getTimeZone())) {
					employee.setTimeZone(systemUser.getTimeZone());
					isEmpModified = true;
				}
				if (isEmpModified) {
					updateList.add(employee);
				}
				systemUser.setPreviousLoginTime(systemUser.getCurrentLoginTime());
				systemUser.setCurrentLoginTime(LocalDateTime.now(ZoneId.of(AppConstants.DEFAULT_TIME_ZONE)));
				updateList.add(systemUser);
				getAuthenticationDAO().updateUserLastLogin(updateList);
			} catch (DataServiceException e) {
				LOG.error(e.getMessage(), e);
				throwServiceException(e.getMessage(), e);
			}
		}
	}

}
