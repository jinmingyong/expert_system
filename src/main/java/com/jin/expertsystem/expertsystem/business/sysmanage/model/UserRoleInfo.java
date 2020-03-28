package com.jin.expertsystem.expertsystem.business.sysmanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author jinmingyong
 * @date 2020/3/13 17:28
 */
//自己定义的用户和角色实体
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleInfo {
    /**
     * id主键
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    /**
     * 性别
     */
    private String sex;

    /**
     * 电话号
     */
    private String phone;

    /**
     * 状态，0：不可用，1：可用
     */
    private String status;

    /**
     * 角色Id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

}
