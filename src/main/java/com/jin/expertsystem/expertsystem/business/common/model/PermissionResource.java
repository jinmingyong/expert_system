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
@Table ( name ="permission_resource" )
public class PermissionResource {


   	@Column(name = "id" )
	@Id
	private Integer id;

	/**
	 * 权限id
	 */
   	@Column(name = "permission_id" )
	private Integer permissionId;

	/**
	 * 资源id
	 */
   	@Column(name = "resource_id" )
	private Integer resourceId;

}
