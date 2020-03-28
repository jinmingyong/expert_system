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
@Table ( name ="roles" )
public class Roles {


	/**
	 * 角色id
	 */
	@Id
   	@Column(name = "role_id" )
	private Integer roleId;

	/**
	 * 角色编号
	 */
   	@Column(name = "role_number" )
	private String roleNumber;

	/**
	 * 角色名称
	 */
   	@Column(name = "role_name" )
	private String roleName;

}
