package com.revature.security.boot.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the SEED_SYSTEM_ROLES database table.
 * 
 */
@Entity
@Table(name = "seed_system_roles")
public class SeedSystemRole implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "code")
  private String code;

  @Column(name = "description")
  private String description;

  @Column(name = "hierarchy")
  private BigDecimal hierarchy;

  @Column(name = "IS_ACTIVE")
  private Boolean isActive;

  @Column(name = "name")
  private String name;


  @Transient
  private Boolean isOrgRoleActive = true;

  public SeedSystemRole() {
    super();
  }

  public SeedSystemRole(String name, String isActive) {
    super();
    this.name = name;
    boolean status = (Integer.parseInt(isActive) > 0) ? true : false;
    this.isActive = isActive == null ? false : status;
  }



  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getHierarchy() {
    return this.hierarchy;
  }

  public void setHierarchy(BigDecimal hierarchy) {
    this.hierarchy = hierarchy;
  }

  public Boolean getIsActive() {
    return this.isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getIsOrgRoleActive() {
    return isOrgRoleActive;
  }

  public void setIsOrgRoleActive(Boolean isOrgRoleActive) {
    this.isOrgRoleActive = isOrgRoleActive;
  }

  @Override
  public int hashCode() {
    return id != null ? id.intValue() : -1;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof SeedSystemRole && hashCode() == obj.hashCode();
  }
}
