package com.jin.expertsystem.expertsystem.business.login.service.impl;

import com.jin.expertsystem.expertsystem.base.jwt.JwtUser;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonUsersDao;
import com.jin.expertsystem.expertsystem.business.common.model.Permissions;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.sysmanage.dao.PermissionsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/14 21:24
 */
//获得用户的角色
@Primary//优选实现次接口
@Service
public class MyUserDetailServiceimpl implements UserDetailsService {
    @Autowired
    private PermissionsDao permissionsDao;
    @Autowired
    private CommonUsersDao commonUsersDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users = new Users();
        users.setUsername(s);
        Users user = commonUsersDao.selectOne(users);
        if (user == null) {
            throw new UsernameNotFoundException("登录名不存在");
        }
        // 查询用户权限
        List<Permissions> permissionsList = permissionsDao.listPermissions(s);
        JwtUser jwtUser = new JwtUser(user.getId(),user.getUsername(),user.getPassword(),permissionsList);
        return jwtUser;
    }
}
