package com.revature.security.boot.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@FilterDef(name = "employeeFilter", parameters = @ParamDef(name = "isActive", type = "boolean"))
@Table(name = "employee_groups")
public class EmployeeGroup implements Comparable<EmployeeGroup> {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column(name = "GROUP_NAME")
  String groupName;

  @Column(name = "DESCRIPTION")
  String description;

  @ManyToMany
  @JoinTable(name = "employee_group_members", joinColumns = @JoinColumn(name = "GROUP_ID"),
      inverseJoinColumns = @JoinColumn(name = "USER_ID"))
  @Filter(name = "employeeFilter", condition = ":isActive")
  @OrderBy("firstName")
  @JsonIgnore
  List<Employee> groupMembers;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Employee> getGroupMembers() {
    return groupMembers;
  }

  public void setGroupMembers(List<Employee> groupMembers) {
    this.groupMembers = groupMembers;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("groupName", groupName)
        .append("description", description).build();
  }

  @Override
  public int hashCode() {
    return id != null ? id.intValue() : super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    boolean result = false;
    if (obj == this) {
      result = true;
    } else if (obj instanceof EmployeeGroup) {
      EmployeeGroup o = (EmployeeGroup) obj;
      if (id != null && o.id != null) {
        result = id.equals(o.id);
      }
    }
    return result;
  }

  @Override
  public int compareTo(EmployeeGroup o) {
    return id.compareTo(o.id);
  }
}
