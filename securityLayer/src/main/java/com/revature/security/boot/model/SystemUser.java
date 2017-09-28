package com.revature.security.boot.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.revature.security.utils.CustomJsonDateDeserializer;
import com.revature.security.utils.JsonDateSerializer;
import com.revature.security.utils.LocalDateAttributeConverter;

@Entity
@Table(name = "system_users")
@XmlRootElement
public class SystemUser implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "username", nullable = false, unique = true)
  private String userName;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Column(name = "created_by", nullable = true)
  private Long createdBy;

  @Column(name = "created_on", nullable = false)
  @Convert(converter = LocalDateAttributeConverter.class)
  @JsonSerialize(using = JsonDateSerializer.class)
  @JsonDeserialize(using = CustomJsonDateDeserializer.class)
  private transient LocalDateTime createdOn;

  @Column(name = "current_login_time")
  @Convert(converter = LocalDateAttributeConverter.class)
  @JsonSerialize(using = JsonDateSerializer.class)
  @JsonDeserialize(using = CustomJsonDateDeserializer.class)
  private transient LocalDateTime currentLoginTime;

  @Column(name = "previous_login_time")
  @Convert(converter = LocalDateAttributeConverter.class)
  @JsonSerialize(using = JsonDateSerializer.class)
  @JsonDeserialize(using = CustomJsonDateDeserializer.class)
  private transient LocalDateTime previousLoginTime;

  @Column(name = "updated_by", nullable = true)
  private Long updatedBy;

  @Column(name = "updated_on", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedOn;

  @Column(name = "password_encrypt", nullable = false)
  private byte[] passwordEncrypt;

  @Column(name = "password_salt", nullable = false)
  private byte[] passwordSalt;

  @Column(name = "password_reset_request", nullable = true)
  private Boolean passwordResetRequest;

  @Column(name = "password_reset_token", nullable = true)
  private String passwordResetToken;

  @ManyToOne
  @JoinColumn(name = "EMP_ID")
  private Employee employee;

  @Column(name = "IS_FIRST_LOGIN")
  private Boolean isFirstLogin;

  public SystemUser() {
    super();
  }

  // ---------------- @TRANSIENT Variables [STARTS]--------------

  @Transient
  private String ip;

  @Transient
  private String location;

  @Transient
  private String timeZone;

  @Transient
  private Locale locale;

  @Transient
  private String sessionId;

  @Transient
  private Boolean activeStatus;

  @Transient
  private String displayFirstName;

  @Transient
  private String displayLastName;

  // --- This For Email Free marker Template ------
  @Transient
  private String displayFullName;

  @Transient
  private String displayRoles;

  @Transient
  private String password;

  // --- This For Email Free marker Template ------
  @Transient
  private String displayEmail;

  @Transient
  private boolean killSessionManually;

  @Transient
  private boolean fromIdleMonitor;

  @Transient
  private SeedSystemRole currentSystemRole;

  @Transient
  private SeedSystemRole switchUserSystemRole;

  @Transient
  private List<SeedSystemRole> systemUserRoles;

  @Transient
  private List<OrgSystemUser> orgSystemUsers;

  @Transient
  private String loginToken;

  @Transient
  private boolean validEmployee;

  // ---------------- @TRANSIENT Variables [ENDS]----------------

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(Date updatedOn) {
    this.updatedOn = updatedOn;
  }

  public LocalDateTime getCurrentLoginTime() {
    return currentLoginTime;
  }

  public void setCurrentLoginTime(LocalDateTime currentLoginTime) {
    this.currentLoginTime = currentLoginTime;
  }

  public LocalDateTime getPreviousLoginTime() {
    return previousLoginTime;
  }

  public void setPreviousLoginTime(LocalDateTime previousLoginTime) {
    this.previousLoginTime = previousLoginTime;
  }

  public byte[] getPasswordEncrypt() {
    return passwordEncrypt;
  }

  public void setPasswordEncrypt(byte[] passwordEncrypt) {
    this.passwordEncrypt = passwordEncrypt;
  }

  public byte[] getPasswordSalt() {
    return passwordSalt;
  }

  public void setPasswordSalt(byte[] passwordSalt) {
    this.passwordSalt = passwordSalt;
  }

  public Boolean getPasswordResetRequest() {
    return passwordResetRequest;
  }

  public void setPasswordResetRequest(Boolean passwordResetRequest) {
    this.passwordResetRequest = passwordResetRequest;
  }

  public String getPasswordResetToken() {
    return passwordResetToken;
  }

  public void setPasswordResetToken(String passwordResetToken) {
    this.passwordResetToken = passwordResetToken;
  }

  public boolean isFromIdleMonitor() {
    return fromIdleMonitor;
  }

  public void setFromIdleMonitor(boolean fromIdleMonitor) {
    this.fromIdleMonitor = fromIdleMonitor;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Boolean getIsFirstLogin() {
    return isFirstLogin;
  }

  public void setIsFirstLogin(Boolean isFirstLogin) {
    this.isFirstLogin = isFirstLogin;
  }

  // ****************** @TRANSIENT Getters & Setters [STARTS]***********
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

  public void setDisplayFirstName(String displayFirstName) {
    this.displayFirstName = displayFirstName;
  }

  public void setDisplayLastName(String displayLastName) {
    this.displayLastName = displayLastName;
  }

  public Boolean getActiveStatus() {
    if (getIsActive() != null) {
      activeStatus = getIsActive() ? true : false;
    }
    return activeStatus;
  }

  public void setActiveStatus(Boolean activeStatus) {
    this.activeStatus = activeStatus;
  }

  public void setDisplayRoles(String displayRoles) {
    this.displayRoles = displayRoles;
  }

  public void setDisplayFullName(String displayFullName) {
    this.displayFullName = displayFullName;
  }

  public void setDisplayEmail(String displayEmail) {
    this.displayEmail = displayEmail;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public boolean isKillSessionManually() {
    return killSessionManually;
  }

  public void setKillSessionManually(boolean killSessionManually) {
    this.killSessionManually = killSessionManually;
  }

  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }

  public String getDisplayFirstName() {
    return displayFirstName;
  }

  public String getDisplayLastName() {
    return displayLastName;
  }

  // --- This For Email Free marker Template ------
  public String getDisplayFullName() {
    if (employee != null) {
      displayFullName = (employee.getFirstName() == null ? "" : employee.getFirstName()) + " "
          + (employee.getLastName() == null ? "" : employee.getLastName());
      displayEmail = employee.getEmailId();
      return displayFullName;
    }
    return displayFullName;
  }

  public String getDisplayRoles() {
    return displayRoles;
  }

  public String getDisplayEmail() {
    return displayEmail;
  }

  public SeedSystemRole getCurrentSystemRole() {
    return currentSystemRole;
  }

  public void setCurrentSystemRole(SeedSystemRole currentSystemRole) {
    this.currentSystemRole = currentSystemRole;
  }

  public SeedSystemRole getSwitchUserSystemRole() {
    return switchUserSystemRole;
  }

  public void setSwitchUserSystemRole(SeedSystemRole switchUserSystemRole) {
    this.switchUserSystemRole = switchUserSystemRole;
  }

  public List<SeedSystemRole> getSystemUserRoles() {
    return systemUserRoles;
  }

  public void setSystemUserRoles(List<SeedSystemRole> systemUserRoles) {
    this.systemUserRoles = systemUserRoles;
  }

  public List<OrgSystemUser> getOrgSystemUsers() {
    return orgSystemUsers;
  }

  public void setOrgSystemUsers(List<OrgSystemUser> orgSystemUsers) {
    this.orgSystemUsers = orgSystemUsers;
  }

  public String getLoginToken() {
    return loginToken;
  }

  public void setLoginToken(String loginToken) {
    this.loginToken = loginToken;
  }

  // ---------------------------------------------------------------- POJO
  // validation
  public boolean isValidEmployee() {
    return this.employee != null && this.employee.getId() != null;
  }

  public void setValidEmployee(boolean validEmployee) {
    this.validEmployee = validEmployee;
  }

  // -------------------------------------------------------------- Hash code
  // override

  // ****************** @TRANSIENT Getters & Setters [ENDS]***********

  @Override
  public int hashCode() {
    return id != null ? id.intValue() : super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    boolean result = false;
    if (obj != null && this.hashCode() == obj.hashCode()) {
      result = true;
    }
    return result;
  }

}
