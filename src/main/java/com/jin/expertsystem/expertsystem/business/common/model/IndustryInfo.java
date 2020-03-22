package com.jin.expertsystem.expertsystem.business.common.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="industry_info" )
public class IndustryInfo {


	/**
	 * 行业主键
	 */
   	@Column(name = "id" )
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 所属行业
	 */
   	@Column(name = "industry" )
	private String industry;

	/**
	 * 状态
	 */
   	@Column(name = "status" )
	private Integer status;

}
