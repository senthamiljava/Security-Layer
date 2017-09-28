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
 * The persistent class for the ORGANIZATION_SYSTEM_USER_ROLES database table.
 * 
 */
@Entity
@Table(name = "org_system_user_roles")
public class OrgSystemUserRole implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "org_system_user_id")
  private OrgSystemUser orgSystemUser;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private SeedSystemRole seedSystemRole;

  @Column(name = "is_active")
  private Boolean isActive = true;

  public OrgSystemUserRole() {
    super();
  }

  public OrgSystemUserRole(OrgSystemUser orgSystemUser, SeedSystemRole seedSystemRole) {
    this.orgSystemUser = orgSystemUser;
    this.seedSystemRole = seedSystemRole;
  }

  // ----------------- Transient value -------------------------

  @Transient
  private List<SeedSystemRole> systemUserRoles;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrgSystemUser getOrgSystemUser() {
    return orgSystemUser;
  }

  public void setOrgSystemUser(OrgSystemUser orgSystemUser) {
    this.orgSystemUser = orgSystemUser;
  }

  public SeedSystemRole getSeedSystemRole() {
    return seedSystemRole;
  }

  public void setSeedSystemRole(SeedSystemRole seedSystemRole) {
    this.seedSystemRole = seedSystemRole;
  }

  // -------------------------------------------- Transient Getter & Setter
  public List<SeedSystemRole> getSystemUserRoles() {
    return systemUserRoles;
  }

  public void setSystemUserRoles(List<SeedSystemRole> systemUserRoles) {
    this.systemUserRoles = systemUserRoles;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  // --------------------------------------------------- Hash code Override


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
