package com.jin.expertsystem.expertsystem.business.sysmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 仅供个人中心-个人信息 信息查询所用
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo{

    /**
     * uuid
     */
    private String userId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别(0：女，1：男)
     */
    private Integer gender;

    /**
     * 政治面貌名称
     */
    private String politicalName;

    /**
     * 职务ids
     */
    private String jobIds;

    /**
     * 工作单位名称
     */
    private String departmentName;

    /**
     * 联系方式（电话号码）
     */
    private String phoneNumber;


}
