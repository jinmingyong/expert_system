package com.jin.expertsystem.expertsystem.business.common.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="company_info" )
public class CompanyInfo {


	/**
	 * 单位id
	 */
   	@Column(name = "id" )
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 单位名称
	 */
   	@Column(name = "company" )
	private String company;

	/**
	 * 状态
	 */
   	@Column(name = "status" )
	private Integer status;

}
