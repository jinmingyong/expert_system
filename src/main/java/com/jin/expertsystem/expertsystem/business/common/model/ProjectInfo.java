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
@Table ( name ="project_info" )
public class ProjectInfo {


	/**
	 * 项目编号，主键
	 */
	@Id
   	@Column(name = "project_id" )
	private String projectId;

	/**
	 * 项目名称
	 */
   	@Column(name = "pro_name" )
	private String proName;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 评审日期
	 */
   	@Column(name = "complete_time" )
	private Date completeTime;

	/**
	 * 抽取次数
	 */
   	@Column(name = "extract_num" )
	private Integer extractNum;

	/**
	 * 补抽次数
	 */
   	@Column(name = "more_ext_num" )
	private Integer moreExtNum;

	/**
	 * 项目备注
	 */
   	@Column(name = "pro_remark" )
	private String proRemark;

}
