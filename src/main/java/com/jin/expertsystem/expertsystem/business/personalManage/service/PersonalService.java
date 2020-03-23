package com.jin.expertsystem.expertsystem.business.personalManage.service;

import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jinmingyong
 * @date 2020/3/23 18:26
 */
public interface PersonalService {
    UserRoleInfo selectUserInfoByToken(HttpServletRequest request);
}
