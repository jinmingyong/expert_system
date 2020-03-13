package com.jin.expertsystem.expertsystem.business.login.service;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/5/15
 */
public interface LoginService {

    /**
     * 登陆
     */
    Result login(String username, String password);


    Result register(Users user);


  //  Result refresh(String oldToken);


 // List<MenuInfo> queryMenuList(HttpServletRequest request);*/

    UserRoleInfo getUserInfoByToken(HttpServletRequest request);
}
