package com.jin.expertsystem.expertsystem.business.login.service.impl;


import com.jin.expertsystem.expertsystem.base.jwt.JwtUserFactory;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonUsersDao;
import com.jin.expertsystem.expertsystem.business.common.model.Permissions;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.sysmanage.dao.PermissionsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 重写UserDetailsService 的方法
 */
@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PermissionsDao permissionsDao;
    @Autowired
    private CommonUsersDao commonUsersDao;


    /*1.通过username 获得信息
    * 2.通过User(UserDetails)返回UserDetails
    * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = new Users();
        users.setUsername(username);
        Users user = commonUsersDao.selectOne(users);
        if (user == null) {
            throw new UsernameNotFoundException("登录名不存在");
        }
        // 查询用户权限
        List<Permissions> permissionsList = permissionsDao.listPermissions(username);
        return JwtUserFactory.create(user, permissionsList);

    }
}
