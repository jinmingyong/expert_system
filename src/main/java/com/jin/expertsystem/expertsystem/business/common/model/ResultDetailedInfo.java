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
@Table ( name ="result_detailed_info" )
public class ResultDetailedInfo {


	/**
	 * 编号
	 */
   	@Column(name = "id" )
	@Id
	private Integer id;

	/**
	 * 抽取结果id
	 */
   	@Column(name = "res_id" )
	private String resId;

	/**
	 * 专家id
	 */
   	@Column(name = "exp_id" )
	private String expId;

	/**
	 * 信息发送状态，0：未发生，1：发送成功
	 */
   	@Column(name = "flag_email" )
	private String flagEmail;

	/**
	 * 确认状态，0：未确认，1：参加，2：不参加
	 */
   	@Column(name = "flag_sts" )
	private String flagSts;

}
