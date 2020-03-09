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
@Table ( name ="users" )
public class Users {


	/**
	 * id主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 用户名
	 */
   	@Column(name = "username" )
	private String username;

	/**
	 * 密码
	 */
   	@Column(name = "password" )
	private String password;

	/**
	 * 类型
	 */
   	@Column(name = "type" )
	private Integer type;

}
