package com.jin.expertsystem.expertsystem.business.common.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="major_info" )
public class MajorInfo {


	/**
	 * 专业id
	 */
   	@Column(name = "id" )
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 专业
	 */
   	@Column(name = "major" )
	private String major;

	/**
	 * 状态
	 */
   	@Column(name = "status" )
	private Integer status;

}
