package com.jin.expertsystem.expertsystem.business.sysmanage.dao;

import com.jin.expertsystem.expertsystem.business.common.model.Permissions;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/5/15
 */
@Repository
public interface PermissionsDao extends Mapper<Permissions> {

    @Select("<script>" +
            "SELECT p.permission_code\n" +
            "FROM users u,role_user ru, role_permission rp , permissions p\n" +
            " WHERE u.id = ru.user_id  and ru.role_id = rp.role_id  and  rp.permission_id = p.permission_id and u.username = #{username}" +
            "</script>")
    List<Permissions> listPermissions(@Param("username") String username);


}
