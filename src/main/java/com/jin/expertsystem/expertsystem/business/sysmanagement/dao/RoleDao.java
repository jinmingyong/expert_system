package com.jin.expertsystem.expertsystem.business.sysmanagement.dao;

import com.jin.expertsystem.expertsystem.business.common.model.Permissions;
import com.jin.expertsystem.expertsystem.business.common.model.RolePermission;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JiChao on 2019/5/16.
 * 角色管理
 */
@Repository
public interface RoleDao {

    /**
     * 通过角色id查询所拥有的权限
     * @param roleId
     * @return
     */
    @Select("<script>select p.* from permissions p " +
            "left join role_permission rp on rp.permission_id = p.permission_id " +
            "where rp.role_id = #{roleId}</script>")
    @ResultType(Permissions.class)
    List<Permissions> selectPermByRoleId(@Param("roleId") String roleId);

    /**
     * 通过角色id查询用户信息
     * @param roleId
     * @return
     */
    @Select("select u.* from users u " +
            "left join role_user ru on ru.user_id = u.user_id " +
            "where user_status = 0 and ru.role_id = #{roleId} " +
            "order by u.login_name asc")
    @ResultType(Permissions.class)
    List<Users> selectUsersByRoleId(@Param("roleId") String roleId);

    /**
     * 通过角色id和组织id查询用户信息
     * @param roleId
     * @return
     */
    @Select("select u.* from users u " +
            "left join role_user ru on ru.user_id = u.user_id " +
            "where department_id = #{deptId} and user_status = 0 and ru.role_id = #{roleId} " +
            "order by u.login_name asc")
    @ResultType(Permissions.class)
    List<Users> selectUsersByRoleIdAndDeptId(@Param("roleId") String roleId, @Param("deptId") int deptId);

    /**
     * 批量新增 角色权限中间表
     * @param saveList
     * @return
     */
    @Insert("<script>" +
            "<foreach collection=\"list\" item=\"item\" separator=\";\">" +
            "insert into role_permission(role_id, permission_id) values(#{item.roleId}, #{item.permissionId})" +
            "</foreach>" +
            "</script>")
    Integer batchSave(List<RolePermission> saveList);

    /**
     * 根据用户id和角色id删除
     */
    @Delete("delete from role_user where user_id = #{userId} and role_id =#{roleId}")
    Integer deleteByUserIdRoleId(@Param("userId") String userId, @Param("roleId") Integer roleId);
}
