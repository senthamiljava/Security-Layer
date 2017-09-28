package com.revature.security.boot.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.security.utils.AppConstants;
import com.revature.security.utils.LocalDateAttributeConverter;

/**
 * The persistent class for the EMPLOYEES database table.
 * 
 */
@Entity
@Table(name = "employees")
public class Employee implements Serializable, Comparable<Employee> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @JsonIgnore
  @Convert(converter = LocalDateAttributeConverter.class)
  @Column(name = "CREATED_ON")
  private transient LocalDateTime createdOn;

  @JsonIgnore
  @Column(name = "DOB")
  private Date dob;

  @Column(name = "EMAIL_ID")
  private String emailId;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "IS_ACTIVE")
  private Boolean isActive;

  @Column(name = "LAST_NAME")
  private String lastName;

  @JsonIgnore
  @Column(name = "OFFICE_ID")
  private String officeId;

  @JsonIgnore
  @Temporal(TemporalType.DATE)
  @Column(name = "UPDATED_ON")
  private Date updatedOn;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "CREATED_BY")
  private SystemUser createdBy;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "UPDATED_BY")
  private SystemUser updatedBy;

  @JsonIgnore
  @Column(name = "IS_VERIFIED")
  private Boolean isVerified;

  @JsonIgnore
  @Column(name = "TITLE")
  private String title;

  @JsonIgnore
  @Column(name = "PHONE_NO")
  private String mobileNo;

  @JsonIgnore
  @Column(name = "SALESFORCE_ID")
  private String salesforceId;

  @JsonIgnore
  @Column(name = "IS_SHOW_APPLY_NOW")
  private Boolean isShowRecruiterOnApplyNow;

  @JsonIgnore
  @Column(name = "FILE_NAME")
  private String fileName;

  @JsonIgnore
  @Column(name = "UNIQUE_NAME")
  private String uniqueName;

  @Column(name = "EMPLOYEE_TIMEZONE")
  private String timeZone;

  @ManyToOne
  @JoinColumn(name = "COUNTRY_ID", nullable = true)
  private Country country;

  @ManyToMany(mappedBy = "groupMembers")
  @JsonIgnore
  @OrderBy("id")
  private transient List<EmployeeGroup> employeeGroups;

  @ManyToOne
  @JoinColumn(name = "ORG_ID", nullable = true)
  private Organization organization;

  @Column(name = "IS_POINT_OF_CONTACT", nullable = true)
  private Boolean isPointOfContact = false;

  @Column(name = "is_enrollment_admin")
  private Boolean isEnrollmentAdmin;

  // ------------ @ TRANSIEN -----------[Start]
  @Transient
  private String userStatus;

  @Transient
  private String fullName;

  @Transient
  private boolean checkBox;

  @Transient
  private Long mentId;

  @Transient
  private String imageUrl;

  @Transient
  private Long orgId;
  // ------[End] ---------

  public Employee() {}

  public Employee(Long empId) {
    this.id = empId;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public String getEmailId() {
    return this.emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Boolean getIsActive() {
    return this.isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getUniqueName() {
    return uniqueName;
  }

  public void setUniqueName(String uniqueName) {
    this.uniqueName = uniqueName;
  }

  public String getOfficeId() {
    return this.officeId;
  }

  public void setOfficeId(String officeId) {
    this.officeId = officeId;
  }

  public Date getUpdatedOn() {
    return this.updatedOn;
  }

  public void setUpdatedOn(Date updatedOn) {
    this.updatedOn = updatedOn;
  }

  public SystemUser getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(SystemUser createdBy) {
    this.createdBy = createdBy;
  }

  public SystemUser getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(SystemUser updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public Boolean getIsVerified() {
    return isVerified;
  }

  public void setIsVerified(Boolean isVerified) {
    this.isVerified = isVerified;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

  public String getSalesforceId() {
    return salesforceId;
  }

  public void setSalesforceId(String salesforceId) {
    this.salesforceId = salesforceId;
  }

  public Boolean getIsShowRecruiterOnApplyNow() {
    return isShowRecruiterOnApplyNow;
  }

  public void setIsShowRecruiterOnApplyNow(Boolean isShowRecruiterOnApplyNow) {
    this.isShowRecruiterOnApplyNow = isShowRecruiterOnApplyNow;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public List<EmployeeGroup> getEmployeeGroups() {
    return employeeGroups;
  }

  public void setEmployeeGroups(List<EmployeeGroup> employeeGroups) {
    this.employeeGroups = employeeGroups;
  }

  // ------------ @ TRANSIEN -----------[Start]

  public String getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(String userStatus) {
    this.userStatus = userStatus;
  }

  public Long getMentId() {
    return mentId;
  }

  public void setMentId(Long mentId) {
    this.mentId = mentId;
  }

  public boolean isCheckBox() {
    return checkBox;
  }

  public void setCheckBox(boolean checkBox) {
    this.checkBox = checkBox;
  }

  public Long getOrgId() {
    return orgId;
  }

  public void setOrgId(BigInteger orgId) {
    this.orgId = orgId == null ? null : orgId.longValue();
  }

  public String getFullName() {
    if (firstName != null && !firstName.trim().equals("") && lastName != null
        && !lastName.trim().equals("")) {
      setFullName(firstName + " " + lastName);
    }
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  // ------------ @ TRANSIEN -----------[End]

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  public Boolean getIsEnrollmentAdmin() {
    return isEnrollmentAdmin;
  }

  public void setIsEnrollmentAdmin(Boolean isEnrollmentAdmin) {
    this.isEnrollmentAdmin = isEnrollmentAdmin;
  }

  public Boolean getIsPointOfContact() {
    return isPointOfContact;
  }

  public void setIsPointOfContact(Boolean isPointOfContact) {
    this.isPointOfContact = isPointOfContact;
  }

  // --------- setters for hibernate result transformers start --------
  public void setEmpId(Object obj) {
    if (obj != null) {
      this.id = Long.parseLong(obj.toString());
    }
  }

  public void setMeId(Object obj) {
    if (obj != null) {
      this.mentId = Long.parseLong(obj.toString());
    }
  }

  public String getImageUrl() {
    if (getId() != null) {
      String baseLocation = AppConstants.getEnv("BASE_DOWNLOAD_URL");
      String imageUrlTmp = AppConstants.EMPLOYEE_IMAGE_DOWNLOAD_URL;
      if (getUniqueName() != null) {
        imageUrlTmp = imageUrlTmp.replace("{id}", getUniqueName());
        this.imageUrl = baseLocation + imageUrlTmp;
      }
    }
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  // --------- setters for hibernate result transformers start --------

  @Override
  public int hashCode() {
    return id != null ? id.intValue() : super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    boolean result = false;
    if (obj == this) {
      result = true;
    } else if (obj instanceof Employee) {
      Employee o = (Employee) obj;
      if (id != null && o.id != null) {
        result = id.equals(o.id);
      }
    }
    return result;
  }

  @Override
  public int compareTo(Employee o) {
    return id.compareTo(o.id);
  }
}
