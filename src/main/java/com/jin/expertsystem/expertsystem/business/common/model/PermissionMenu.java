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
@Table ( name ="permission_menu" )
public class PermissionMenu {


	/**
	 * id
	 */
   	@Column(name = "id" )
	@Id
	private Integer id;

	/**
	 * 权限id，用来和权限表的id做关联
	 */
   	@Column(name = "permission_id" )
	private Integer permissionId;

	/**
	 * 菜单id，用来和菜单表的id做关联
	 */
   	@Column(name = "menu_id" )
	private Integer menuId;

}
