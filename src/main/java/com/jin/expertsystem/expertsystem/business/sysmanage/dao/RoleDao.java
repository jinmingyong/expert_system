package com.jin.expertsystem.expertsystem.business.sysmanage.dao;

import com.jin.expertsystem.expertsystem.business.common.model.PermissionResource;
import com.jin.expertsystem.expertsystem.business.common.model.RolePermission;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/26 23:54
 */

@Repository
public interface RoleDao {
    /**
     * 批量添加
     * @param saveList
     * @return
     */
    @Insert("<script>" +
            "<foreach collection=\"list\" item=\"item\" separator=\";\">" +
            "insert into role_permission(role_id, permission_id) values(#{item.roleId}, #{item.permissionId})" +
            "</foreach>" +
            "</script>")
    Integer batchSavePermission(List<RolePermission> saveList);
}
