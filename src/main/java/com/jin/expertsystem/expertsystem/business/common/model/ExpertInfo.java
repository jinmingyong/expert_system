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
@Table ( name ="expert_info" )
public class ExpertInfo {


	/**
	 * 专家id
	 */
	@Id
   	@Column(name = "expert_id" )
	private String expertId;

	/**
	 * 专家姓名
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 年龄
	 */
   	@Column(name = "age" )
	private Integer age;

	/**
	 * 性别
	 */
   	@Column(name = "sex" )
	private String sex;

	/**
	 * 生日
	 */
   	@Column(name = "birthday" )
	private Date birthday;

	/**
	 * 民族
	 */
   	@Column(name = "nationlity" )
	private String nationlity;

	/**
	 * 身份证号
	 */
   	@Column(name = "ic_card" )
	private String icCard;

	/**
	 * 职务
	 */
   	@Column(name = "job" )
	private String job;

	/**
	 * 职称
	 */
   	@Column(name = "job_grade" )
	private String jobGrade;

	/**
	 * 所属行业
	 */
   	@Column(name = "industry" )
	private String industry;

	/**
	 * 工作年限
	 */
   	@Column(name = "working_year" )
	private Integer workingYear;

	/**
	 * 工作单位
	 */
   	@Column(name = "company" )
	private String company;

	/**
	 * 学历
	 */
   	@Column(name = "degree" )
	private String degree;

	/**
	 * 毕业学院
	 */
   	@Column(name = "college" )
	private String college;

	/**
	 * 专业类型
	 */
   	@Column(name = "major" )
	private String major;

	/**
	 * 手机号码
	 */
   	@Column(name = "phone" )
	private String phone;

	/**
	 * 固定座机
	 */
   	@Column(name = "tell_phone" )
	private String tellPhone;

	/**
	 * 电子邮箱
	 */
   	@Column(name = "email" )
	private String email;

	/**
	 * 现在住址
	 */
   	@Column(name = "city" )
	private String city;

	/**
	 * 入库时间
	 */
   	@Column(name = "time" )
	private Date time;

	/**
	 * 状态，0：审核未通过，1：正常，2：已被抽取
	 */
   	@Column(name = "status" )
	private String status;

	/**
	 * 评估分数
	 */
   	@Column(name = "estimate" )
	private Double estimate;

	/**
	 * 头像
	 */
   	@Column(name = "picture" )
	private String picture;

}
