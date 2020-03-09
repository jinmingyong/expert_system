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
@Table ( name ="role_user" )
public class RoleUser {


	/**
	 * 角色用户id
	 */
   	@Column(name = "id" )
	@Id
	private Integer id;

	/**
	 * 用户id，用来和用户表的用户uid进行关联
	 */
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 角色id，用来和角色表的id进行关联
	 */
   	@Column(name = "role_id" )
	private Integer roleId;

}
