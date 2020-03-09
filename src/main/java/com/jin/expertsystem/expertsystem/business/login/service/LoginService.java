package com.jin.expertsystem.expertsystem.business.login.service;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.login.model.MenuInfo;
import com.jin.expertsystem.expertsystem.business.sysmanagement.model.UserRoleInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/5/15
 */
public interface LoginService {

    /**
     * 登陆
     *
     * @param userNumber
     * @param password
     * @return String
     */
    Result login(String userNumber, String password);

    /**
     * 注册
     *
     * @param user
     */
    Result register(Users user);

    /**
     * 刷新token
     * @param oldToken
     * @return String
     */
    Result refresh(String oldToken);

    /**
     *根据权限查询菜单列表
     * @param request
     * @return
     */
    List<MenuInfo> queryMenuList(HttpServletRequest request);

    /**
     * 通过token获取用户信息
     * @param request
     * @return
     */
    UserRoleInfo getUserInfoByToken(HttpServletRequest request);
}
