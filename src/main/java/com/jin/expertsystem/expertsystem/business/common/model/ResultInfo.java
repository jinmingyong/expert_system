package com.jin.expertsystem.expertsystem.business.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="result_info" )
public class ResultInfo {


	/**
	 * 抽取结果id
	 */
   	@Column(name = "result_id" )
	private String resultId;

	/**
	 * 项目id
	 */
   	@Column(name = "pro_id" )
	private String proId;

	/**
	 * 抽取类型，0：正常，1：补抽
	 */
   	@Column(name = "type" )
	private String type;

   	@Column(name = "res_create_time" )
	private Date resCreateTime;

}
