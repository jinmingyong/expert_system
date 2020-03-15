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
@Table ( name ="role_permission" )
public class RolePermission {


	/**
	 * 角色权限id
	 */
   	@Column(name = "id" )
	@Id
	private Integer id;

	/**
	 * 角色id
	 */
   	@Column(name = "role_id" )
	private Integer roleId;

	/**
	 * 权限id
	 */
   	@Column(name = "permission_id" )
	private Integer permissionId;

}