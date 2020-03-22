package com.jin.expertsystem.expertsystem.business.common.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="jobgrade_info" )
public class JobgradeInfo {


	/**
	 * 职称id
	 */
   	@Column(name = "id" )
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 职称
	 */
   	@Column(name = "job_grade" )
	private String jobGrade;

	/**
	 * 状态
	 */
   	@Column(name = "status" )
	private Integer status;

}
