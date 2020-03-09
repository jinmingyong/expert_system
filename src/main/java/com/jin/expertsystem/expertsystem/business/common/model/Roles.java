package com.jin.expertsystem.expertsystem.business.common.model;


public class Roles {

  private long roleId;
  private String roleNumber;
  private String roleName;
  private String roleFeatures;
  private long roleStatus;


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }


  public String getRoleNumber() {
    return roleNumber;
  }

  public void setRoleNumber(String roleNumber) {
    this.roleNumber = roleNumber;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public String getRoleFeatures() {
    return roleFeatures;
  }

  public void setRoleFeatures(String roleFeatures) {
    this.roleFeatures = roleFeatures;
  }


  public long getRoleStatus() {
    return roleStatus;
  }

  public void setRoleStatus(long roleStatus) {
    this.roleStatus = roleStatus;
  }

}
