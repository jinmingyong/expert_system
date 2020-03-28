package com.jin.expertsystem.expertsystem.business.common.dao;

import com.jin.expertsystem.expertsystem.business.common.model.CompanyInfo;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.common.need.MyMapper;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author JMY
* @date 2020/03/17
*/
@Repository
public interface CommonUsersDao extends MyMapper<Users> {

    @Select("<script>" +
            "SELECT\n" +
            "users.username,\n" +
            "users.`password`,\n" +
            "users.sex,\n" +
            "users.phone,\n" +
            "users.`status`,\n" +
            "users.id,\n" +
            "roles.role_id,\n" +
            "roles.role_name\n" +
            "FROM\n" +
            "role_user\n" +
            "LEFT JOIN roles ON role_user.role_id = roles.role_id\n" +
            "LEFT JOIN users ON users.id = role_user.user_id\n" +
            "<where><if test=\"username!=null and username!=''\" > username  LIKE '%${username}%' </if></where>\n"+
            "</script>")
    List<UserRoleInfo> selectAllCompanyInfoByName(@Param("username") String username);

}
