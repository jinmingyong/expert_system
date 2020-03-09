package com.jin.expertsystem.expertsystem.business.login.dao;

import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.sysmanagement.model.UserRoleInfo;
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
     * @param userNumber
     * @return
     */
    @Select("select * from users where login_name = #{userNumber}")
    Users queryUserStatus(String userNumber);

    /**
     * 通过登录名获取用户信息
     * @param loginName
     * @return
     */
    @Select("select \n" +
            "u.user_id,u.open_id,u.login_name,u.reset_password_flag,u.first_login_flag,\n" +
            "u.user_status,u.registered_time,\n" +
            "u.department_id,d.department_name,d.president_name,d.committee_name,\n" +
            "d.member_count,d.address,d.phone_number,d.`status`,d.create_date,\n" +
            "r.role_id,r.role_name\n" +
            "from users u\n" +
            "left join role_user ru on ru.user_id = u.user_id\n" +
            "left join roles r on r.role_id = ru.role_id\n" +
            "left join departments d on d.department_id = u.department_id\n" +
            "where login_name = #{loginName}")
    UserRoleInfo getUserInfoByToken(@Param("loginName") String loginName);
}
