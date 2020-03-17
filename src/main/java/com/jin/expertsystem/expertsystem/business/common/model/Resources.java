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
@Table ( name ="resources" )
public class Resources {


	/**
	 * 资源id
	 */
	@Id
   	@Column(name = "resource_id" )
	private Integer resourceId;

	/**
	 * 资源名称
	 */
   	@Column(name = "resource_name" )
	private String resourceName;

	/**
	 * 资源url，需填入后端接口的地址
	 */
   	@Column(name = "resource_url" )
	private String resourceUrl;

}
