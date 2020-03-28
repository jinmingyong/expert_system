package com.jin.expertsystem.expertsystem.business.personalManage.service;

import com.jin.expertsystem.expertsystem.business.personalManage.model.UpdatePasswordParams;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jinmingyong
 * @date 2020/3/23 18:26
 */
public interface PersonalService {
    //获得用户信息
    UserRoleInfo selectUserInfoByToken(HttpServletRequest request);
    // 修改密码
    int updatePasswordByToken(HttpServletRequest request, UpdatePasswordParams updatePasswordParams);
}
