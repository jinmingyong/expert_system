package com.jin.expertsystem.expertsystem.business.common.model;


public class Menus {

  private long menuId;
  private String menuName;
  private String routeName;
  private String menuUrl;
  private String iconName;
  private long parentId;
  private long showStatus;
  private long displaySequence;


  public long getMenuId() {
    return menuId;
  }

  public void setMenuId(long menuId) {
    this.menuId = menuId;
  }


  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }


  public String getRouteName() {
    return routeName;
  }

  public void setRouteName(String routeName) {
    this.routeName = routeName;
  }


  public String getMenuUrl() {
    return menuUrl;
  }

  public void setMenuUrl(String menuUrl) {
    this.menuUrl = menuUrl;
  }


  public String getIconName() {
    return iconName;
  }

  public void setIconName(String iconName) {
    this.iconName = iconName;
  }


  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }


  public long getShowStatus() {
    return showStatus;
  }

  public void setShowStatus(long showStatus) {
    this.showStatus = showStatus;
  }


  public long getDisplaySequence() {
    return displaySequence;
  }

  public void setDisplaySequence(long displaySequence) {
    this.displaySequence = displaySequence;
  }

}
