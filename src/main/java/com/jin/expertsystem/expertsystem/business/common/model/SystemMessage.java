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
@Table ( name ="system_message" )
public class SystemMessage {


	@Id
   	@Column(name = "message_id" )
	private Integer messageId;

   	@Column(name = "mes_info" )
	private String mesInfo;

}
