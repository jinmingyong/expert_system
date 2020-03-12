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
@Table ( name ="message_manage" )
public class MessageManage {


	/**
	 * 消息id
	 */
   	@Column(name = "mes_id" )
	private String mesId;

	/**
	 * 专家id
	 */
   	@Column(name = "exp_id" )
	private String expId;

	/**
	 * 消息内容
	 */
   	@Column(name = "mes_content" )
	private String mesContent;

	/**
	 * 发送时间
	 */
   	@Column(name = "send_time" )
	private Date sendTime;

	/**
	 * 发送者id
	 */
   	@Column(name = "sender_id" )
	private String senderId;

}
