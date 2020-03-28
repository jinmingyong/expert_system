package com.jin.expertsystem.expertsystem.business.common.model;

import javax.persistence.*;

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
	@GeneratedValue(generator = "JDBC")
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
	 * 性别
	 */
   	@Column(name = "sex" )
	private String sex;

	/**
	 * 电话号
	 */
   	@Column(name = "phone" )
	private String phone;

	/**
	 * 状态，0：不可用，1：可用
	 */
   	@Column(name = "status" )
	private String status;

}
