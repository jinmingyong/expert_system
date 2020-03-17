package com.jin.expertsystem.expertsystem.business.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="menus" )
public class Menus {


	/**
	 * 菜单id
	 */
	@Id
   	@Column(name = "menu_id" )
	private Integer menuId;

	/**
	 * 菜单名称
	 */
   	@Column(name = "menu_name" )
	private String menuName;

	/**
	 * 路由name
	 */
   	@Column(name = "route_name" )
	private String routeName;

	/**
	 * 菜单url，需填入前端页面对应的前端路由地址，如果是父级菜单节点，则可以不填url
	 */
   	@Column(name = "menu_url" )
	private String menuUrl;

	/**
	 * icon参数
	 */
   	@Column(name = "icon_name" )
	private String iconName;

	/**
	 * 父级菜单id
	 */
   	@Column(name = "parent_id" )
	private Integer parentId;

	/**
	 * 是否在左侧显示，1：显示，0：不显示，默认1
	 */
   	@Column(name = "show_status" )
	private Integer showStatus;

	/**
	 * 显示顺序
	 */
   	@Column(name = "display_sequence" )
	private Integer displaySequence;

}
