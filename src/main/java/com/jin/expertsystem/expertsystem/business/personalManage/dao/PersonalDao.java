package com.jin.expertsystem.expertsystem.business.personalManage.dao;

import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author jinmingyong
 * @date 2020/3/23 18:23
 */
@Repository
public interface PersonalDao {
    @Select("SELECT\n" +
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
            "WHERE\n" +
            "users.id = #{id}")
    UserRoleInfo selectUserInfoByToken(@Param("id") String id);

    // 根据用户id查询是否有未确认消息
    @Select("SELECT\n" +
            "Count(message_manage.mes_id) \n" +
            "FROM\n" +
            "message_manage\n" +
            "WHERE\n" +
            "status = 0 and message_manage.exp_id = #{expId}")
    Integer countNewMessageByToken(@Param("expId")String expId);
}
