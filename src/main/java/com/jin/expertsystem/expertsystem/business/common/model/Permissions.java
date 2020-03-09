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
@Table ( name ="permissions" )
public class Permissions {


	/**
	 * 权限id
	 */
   	@Column(name = "permission_id" )
	private Integer permissionId;

	/**
	 * 权限名称
	 */
   	@Column(name = "permission_name" )
	private String permissionName;

	/**
	 * 权限编码
	 */
   	@Column(name = "permission_code" )
	private String permissionCode;

}
