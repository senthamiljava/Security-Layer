package com.revature.security.boot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

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

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "organizations")
public class Organization implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  private String name;

  @Column(name = "alias_name")
  private String aliasName;

  private String type;

  @Column(name = "sf_acccount_id")
  private String sfAccountId;

  @Column(name = "sf_account_name")
  private String sfAccountName;

  @Column(name = "is_content_subscribed")
  private Boolean isContentSubscribed;

  @Column(name = "is_all_content_subscribed")
  private Boolean isAllContentSubscribed;

  @Column(name = "is_active")
  private Boolean isActive;

  @Column(name = "created_on")
  private Date createdOn;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "created_by")
  private Employee createdBy;

  @Column(name = "modified_on")
  private Date modifiedOn;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "modified_by")
  private Employee modifiedBy;

  @Column(name = "is_root_org")
  private Boolean isRootOrganization;

  @Column(name = "is_all_video_subscribed")
  private Boolean isAllVideoSubscribed;

  @Column(name = "is_all_qsn_subscribed")
  private Boolean isAllQsnSubscribed;

  @Column(name = "is_enabled_default_cnt")
  private Boolean isEnabledDefaultContent;

  @Column(name = "is_enabled_user_verification")
  private Boolean isEnabledUserVerification;

  @Column(name = "sync_data_to_salesforce")
  private Boolean syncDataToSalesforce;

  @Column(name = "IS_PAYMENT_OPTION_ENABLED")
  private Boolean isPaymentOptionEnabled;

  @Transient
  private String organizationCode;

  public Organization() {}

  public Organization(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Boolean getIsContentSubscribed() {
    return isContentSubscribed;
  }

  public void setIsContentSubscribed(Boolean isContentSubscribed) {
    this.isContentSubscribed = isContentSubscribed;
  }

  public Boolean getIsAllContentSubscribed() {
    return isAllContentSubscribed;
  }

  public void setIsAllContentSubscribed(Boolean isAllContentSubscribed) {
    this.isAllContentSubscribed = isAllContentSubscribed;
  }

  public String getSfAccountId() {
    return sfAccountId;
  }

  public void setSfAccountId(String sfAccountId) {
    this.sfAccountId = sfAccountId;
  }

  public String getSfAccountName() {
    return sfAccountName;
  }

  public void setSfAccountName(String sfAccountName) {
    this.sfAccountName = sfAccountName;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public Employee getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Employee createdBy) {
    this.createdBy = createdBy;
  }

  public Date getModifiedOn() {
    return modifiedOn;
  }

  public void setModifiedOn(Date modifiedOn) {
    this.modifiedOn = modifiedOn;
  }

  public Employee getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(Employee modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Boolean getIsRootOrganization() {
    return isRootOrganization;
  }

  public void setIsRootOrganization(Boolean isRootOrganization) {
    this.isRootOrganization = isRootOrganization;
  }

  public Boolean getIsAllVideoSubscribed() {
    return isAllVideoSubscribed;
  }

  public void setIsAllVideoSubscribed(Boolean isAllVideoSubscribed) {
    this.isAllVideoSubscribed = isAllVideoSubscribed;
  }

  public Boolean getIsAllQsnSubscribed() {
    return isAllQsnSubscribed;
  }

  public void setIsAllQsnSubscribed(Boolean isAllQsnSubscribed) {
    this.isAllQsnSubscribed = isAllQsnSubscribed;
  }

  public Boolean getIsEnabledDefaultContent() {
    return isEnabledDefaultContent;
  }

  public void setIsEnabledDefaultContent(Boolean isEnabledDefaultContent) {
    this.isEnabledDefaultContent = isEnabledDefaultContent;
  }

  public Boolean getIsEnabledUserVerification() {
    return isEnabledUserVerification;
  }

  public void setIsEnabledUserVerification(Boolean isEnabledUserVerification) {
    this.isEnabledUserVerification = isEnabledUserVerification;
  }

  public Boolean getSyncDataToSalesforce() {
    return syncDataToSalesforce;
  }

  public void setSyncDataToSalesforce(Boolean syncDataToSalesforce) {
    this.syncDataToSalesforce = syncDataToSalesforce;
  }

  public Boolean getIsPaymentOptionEnabled() {
    return isPaymentOptionEnabled;
  }

  public void setIsPaymentOptionEnabled(Boolean isPaymentOptionEnabled) {
    this.isPaymentOptionEnabled = isPaymentOptionEnabled;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (!(obj instanceof Organization)) {
      return false;
    }
    Organization org = (Organization) obj;
    return new EqualsBuilder().append(this.id, org.id).isEquals();
  }

  public void setOrgId(Object o) {
    this.id = Optional.ofNullable(o).map(in -> Long.parseLong(in.toString())).orElse(null);
  }

  public String getAliasName() {
    return aliasName;
  }

  public void setAliasName(String aliasName) {
    this.aliasName = aliasName;
  }

  public String getOrganizationCode() {
    return organizationCode;
  }

  public void setOrganizationCode(String organizationCode) {
    this.organizationCode = organizationCode;
  }
}
