package com.jin.expertsystem.expertsystem.business.common.model;


public class PermissionResource {

  private long id;
  private long permissionId;
  private long resourceId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(long permissionId) {
    this.permissionId = permissionId;
  }


  public long getResourceId() {
    return resourceId;
  }

  public void setResourceId(long resourceId) {
    this.resourceId = resourceId;
  }

}
