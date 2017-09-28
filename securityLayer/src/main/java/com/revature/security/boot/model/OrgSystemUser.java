package com.revature.security.boot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the ORGANIZATION_SYSTEM_USERS database table.
 * 
 */
@Entity
@Table(name = "org_system_users")
public class OrgSystemUser implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "system_user_id")
  private SystemUser systemUser;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "org_id")
  private Organization organization;

  @Column(name = "is_active")
  private Boolean isActive;

  // ----------------- Transient value -------------------------

  @Transient
  private SeedSystemRole currentSystemRole;

  @Transient
  private List<SeedSystemRole> systemUserRoles;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SystemUser getSystemUser() {
    return systemUser;
  }

  public void setSystemUser(SystemUser systemUser) {
    this.systemUser = systemUser;
  }

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  // Transient getter setters starts

  public List<SeedSystemRole> getSystemUserRoles() {
    return systemUserRoles;
  }

  public void setSystemUserRoles(List<SeedSystemRole> systemUserRoles) {
    this.systemUserRoles = systemUserRoles;
  }

  public SeedSystemRole getCurrentSystemRole() {
    return currentSystemRole;
  }

  public void setCurrentSystemRole(SeedSystemRole currentSystemRole) {
    this.currentSystemRole = currentSystemRole;
  }

  // Transient getter setters ends

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
