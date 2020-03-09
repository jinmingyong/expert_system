package com.jin.expertsystem.expertsystem.business.sysmanagement.dao;

import com.jin.expertsystem.expertsystem.business.common.model.RoleUser;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.sysmanagement.model.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JiChao on 2019/5/16.
 */
@Repository
public interface UserDao {

    /**
     * 通过工号获取用户信息
     * @param userNum
     * @return
     */
    @Select("select u.user_id,u.login_name,u.user_name,u.leader_status,u.department_id,d.`level`,\n" +
            "GROUP_CONCAT(r.role_id) as 'roleIds',d.leader_id from users u\n" +
            "left join role_user ru on ru.user_id = u.user_id\n" +
            "left join roles r on r.role_id = ru.role_id\n" +
            "left join departments d on d.department_id = u.department_id\n" +
            "where login_name = #{userNum}")
    UserRoleInfo getUserInfoByToken(@Param("userNum") String userNum);

    /**
     * 通过token获取个人信息
     * @param userNum
     * @return
     */
    @Select("select u.user_id,u.user_name,u.gender,u.job_ids," +
            "u.phone_number,p.political_name,d.department_name " +
            "from users u " +
            "left join political p on p.political_id = u.political_id " +
            "left join departments d on d.department_id = u.department_id " +
            "where u.login_name = #{userNum}")
    PersonalInfo getPersonalInfoByToken(@Param("userNum") String userNum);

    /**
     * 查询用户密码
     * @param userNum
     * @return
     */
    @Select("select password from users where login_name = #{userNum}")
    String getUserPassword(@Param("userNum") String userNum);

    /**
     * 修改用户密码
     * @param userNum
     * @return
     */
    @Update("update users set password = #{password} where login_name = #{userNum}")
    Integer updateUserPassword(@Param("userNum") String userNum, @Param("password") String password);

    /**
     * 根据用户id查用户角色
     */
    @Select("select * from role_user where user_id = #{value}")
    List<RoleUser> selectUserRoleById(String userId);

    /**
     * 根据工号查用户信息
     * @param userNumber
     * @return
     */
    @Select("select user_id,login_name,password,user_name,political_id," +
            "department_id,leader_status,user_status,registered_time " +
            "from users where login_name = #{value}")
    Users selectUserByUserNumber(String userNumber);

    /**
     * 根据工号修改用户状态
     * @param userStatus
     * @param userNumber
     * @return
     */
    @Update("update users set user_status = #{userStatus} where login_name = #{userNumber}")
    int changeUsrStatus(@Param("userStatus") int userStatus, @Param("userNumber") String userNumber);

    /**
     * 根据用户工号修改用户信息
     * @param users
     * @return
     */
    @Update("update users set user_name = #{userName},political_id = #{politicalId}," +
            "phone_number = #{phoneNumber},gender = #{gender} where login_name = #{userNumber}")
    int updateUsrByUsrNum(Users users);

    /**
     * 查询所有用户信息（导入使用）
     * @param
     * @return
     */
    @Select("select user_id,login_name from users")
    List<Users> selectAllUser();

    /**
     * 根据用户id查询用户是否是社团审核员
     * @param userId
     * @return
     */
    @Select("select count(1) from role_user " +
            "where user_id = #{userId} and (role_id = 1 or role_id = 9)")
    int queryIsAssociationChecker(@Param("userId") String userId);
}
