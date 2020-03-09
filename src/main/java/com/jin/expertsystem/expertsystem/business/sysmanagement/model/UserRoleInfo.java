package com.jin.expertsystem.expertsystem.business.sysmanagement.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleInfo {
    /**
     * uuid
     */
    private String userId;

    /**
     * 用户微信openId（如果暂时不用可以空着）
     */
    private String openId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码重置标志位（0：初始密码，1：已修改）
     */
    private Integer resetPasswordFlag;

    /**
     * 首次登录标志位（0：未登录，1：已登录）
     */
    private Integer firstLoginFlag;

    /**
     * 状态（0：用户不可用，1：用户可用）
     */
    private Integer userStatus;

    /**
     * 用户注册时间
     */
    private Date registeredTime;

    /**
     * 所属工作单位
     */
    private Integer departmentId;

    /**
     * 工会名称
     */
    private String departmentName;

    /**
     * 工会主席名称
     */
    private String presidentName;

    /**
     * 委员名称
     */
    private String committeeName;

    /**
     * 会员人数
     */
    private Integer memberCount;

    /**
     * 工会地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 停用标志位（0：停用 1：启用）
     */
    private Integer status;

    /**
     * 角色Id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;
}
