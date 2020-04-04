package com.jin.expertsystem.expertsystem.business.sysmanage.service;

import com.jin.expertsystem.expertsystem.business.common.model.RoleUser;
import com.jin.expertsystem.expertsystem.business.common.model.Users;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jinmingyong
 * @date 2020/3/24 20:12
 */
public interface UsersService {
    //查询名字是否重复
    Boolean selectUsersByUsername(Users users);
    //添加用户
    Integer insertUsersInfo(Users users);

    Integer deleteById(String Id);
    // 更新角色
    Integer updateUserRole(RoleUser roleUser);
}
