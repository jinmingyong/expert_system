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
@Table ( name ="menu_resource" )
public class MenuResource {


	/**
	 * 菜单资源id
	 */
   	@Column(name = "id" )
	@Id
	private Integer id;

	/**
	 * 菜单id，用来和菜单表的id做关联
	 */
   	@Column(name = "menu_id" )
	private Integer menuId;

	/**
	 * 资源id，用来和资源表的id做关联
	 */
   	@Column(name = "resource_id" )
	private Integer resourceId;

}
