package com.jin.expertsystem.expertsystem.business.login.dao;

import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 土豆儿
 * @date 2019/10/21 10:57
 **/

@Repository
public interface LoginDao {

    /**
     * 查询用户账户状态(0：启用， 1：停用)
     * @param username
     * @return
     */
    @Select("select * from users where username = #{username}")
    Users queryUserStatus(String username);

    /**
     * 通过登录名获取用户信息
     * @param username
     * @return
     */
    @Select("SELECT\n" +
            "users.username,\n" +
            "users.`password`,\n" +
            "users.type,\n" +
            "users.sex,\n" +
            "users.phone,\n" +
            "users.`status`,\n" +
            "users.id,\n" +
            "roles.role_id,\n" +
            "roles.role_name\n" +
            "FROM\n" +
            "role_user\n" +
            "LEFT JOIN roles ON role_user.role_id = role_user.user_id\n" +
            "LEFT JOIN users ON users.id = role_user.user_id\n" +
            "WHERE\n" +
            "users.username = #{username}")
    UserRoleInfo getUserInfoByToken(@Param("username") String username);
}
