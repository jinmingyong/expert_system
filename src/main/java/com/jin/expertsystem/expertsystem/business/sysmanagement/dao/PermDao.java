package com.jin.expertsystem.expertsystem.business.sysmanagement.dao;

import com.jin.expertsystem.expertsystem.business.common.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by JiChao on 2019/5/16.
 * 权限管理
 */
public interface PermDao {

    /**
     * 查询权限下拥有的菜单
     * @param permissionId
     * @return
     */
    @Select("<script>" +
            "select m.* from menus m " +
            "left join permission_menu pm on pm.menu_id = m.menu_id " +
            "where pm.permission_id = #{permissionId}" +
            "</script>")
    @ResultType(Menus.class)
    List<Menus> selectMenuForPerm(@Param("permissionId") Integer permissionId);

    /**
     * 查询权限下拥有的资源
     * @param permissionId
     * @return
     */
    @Select("<script>" +
            "select r.* from resources r " +
            "left join permission_resource pr on pr.resource_id = r.resource_id " +
            "where pr.permission_id = #{permissionId}" +
            "</script>")
    @ResultType(Resources.class)
    List<Resources> selectResourceForPerm(@Param("permissionId") Integer permissionId);

    /**
     * 批量添加 权限菜单中间表
     * @param saveList
     * @return
     */
    @Insert("<script>" +
            "<foreach collection=\"list\" item=\"item\" separator=\";\">" +
            "insert into permission_menu(permission_id, menu_id) values(#{item.permissionId}, #{item.menuId})" +
            "</foreach>" +
            "</script>")
    int batchSaveMenu(List<PermissionMenu> saveList);

    /**
     * 批量添加 权限资源中间表
     * @param saveList
     * @return
     */
    @Insert("<script>" +
            "<foreach collection=\"list\" item=\"item\" separator=\";\">" +
            "insert into permission_resource(permission_id, resource_id) values(#{item.permissionId}, #{item.resourceId})" +
            "</foreach>" +
            "</script>")
    int batchSaveResource(List<PermissionResource> saveList);



}
