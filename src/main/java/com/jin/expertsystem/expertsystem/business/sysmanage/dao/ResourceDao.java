package com.jin.expertsystem.expertsystem.business.sysmanage.dao;

import com.jin.expertsystem.expertsystem.business.common.model.Resources;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.AllResource;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.MenuResources;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.PathPermission;
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
public interface ResourceDao extends Mapper<Resources> {

    /**
     * 查出所有的资源权限配置
     * @return List<PathPermission>
     */
    @Select("<script>" +
            "SELECT r.resource_url,p.permission_code" +
            "  FROM permission_resource pr LEFT JOIN resources r ON pr.resource_id = r.resource_id " +
            " LEFT JOIN permissions p ON pr.permission_id = p.permission_id" +
            "</script>")
    List<PathPermission> listPathPermission();




    @Select("<script>" +
            "SELECT \n" +
            "temp.permission_id,\n" +
            "`name`,\n" +
            "url,\n"+
            "parent_id,\n" +
            "type\n" +
            "FROM (\n" +
            "SELECT \n" +
            "permission_id,\n" +
            "menu_name AS name,\n" +
            "menu_url as url,\n"+
            "parent_id,\n" +
            "IF(parent_id=0,1,2) AS type\n" +
            "FROM\n" +
            "permission_menu LEFT JOIN menus on permission_menu.menu_id=menus.menu_id\n" +
            "UNION all\n" +
            "SELECT\n" +
            "permission_id,\n" +
            "resource_name,\n" +
            "resource_url,\n" +
            "IF(menus.menu_id is null,0,menus.menu_id),\n" +
            "3 AS type\n" +
            "FROM\n" +
            "permission_resource \n" +
            "LEFT JOIN resources ON resources.resource_id=permission_resource.resource_id\n" +
            "LEFT JOIN menu_resource on menu_resource.resource_id=permission_resource.permission_id\n" +
            "LEFT JOIN menus ON menus.menu_id=menu_resource.menu_id) temp" +
            "<where><if test=\"resourceName!=null and resourceName!=''\" > name  LIKE '%${resourceName}%' </if></where>\n"+
            "</script>")
    List<AllResource> selectAllResource(@Param("resourceName")String resourceName);




    @Select("<script>" +
            "SELECT \n" +
            "temp.permission_id,\n" +
            "roles.role_id,\n" +
            "`name`,\n" +
            "url,\n" +
            "parent_id,\n" +
            "type\n" +
            "FROM (\n" +
            "SELECT \n" +
            "permission_id,\n" +
            "menu_name AS name,\n" +
            "menu_url AS url,\n" +
            "parent_id,\n" +
            "IF(parent_id=0,1,2) AS type\n" +
            "FROM\n" +
            "permission_menu LEFT JOIN menus on permission_menu.menu_id=menus.menu_id\n" +
            "UNION all\n" +
            "SELECT  \n" +
            "permission_id,\n" +
            "resource_name,\n" +
            "resource_url,\n" +
            "IF(menus.menu_id is null,0,menus.menu_id),\n" +
            "3 AS type\n" +
            "FROM\n" +
            "permission_resource LEFT JOIN resources ON resources.resource_id=permission_resource.resource_id\n" +
            "LEFT JOIN menu_resource on menu_resource.resource_id=permission_resource.permission_id\n" +
            "LEFT JOIN menus ON menus.menu_id=menu_resource.menu_id) temp\n" +
            "LEFT JOIN role_permission ON role_permission.permission_id=temp.permission_id\n" +
            "LEFT JOIN roles ON roles.role_id=role_permission.role_id\n" +
            "<where><if test=\"roleName!=null and roleName!=''\" > role_name  LIKE '%${roleName}%' </if></where>\n"+
            "</script>")
    List<AllResource> selectAllResourceByTree(@Param("roleName")String roleName);




    @Select("<script>" +
            "SELECT \n" +
            "temp.permission_id,\n" +
            "roles.role_id,\n" +
            "`name`,\n" +
            "url,\n" +
            "parent_id,\n" +
            "type\n" +
            "FROM (\n" +
            "SELECT \n" +
            "permission_id,\n" +
            "menu_name AS name,\n" +
            "menu_url AS url,\n" +
            "parent_id,\n" +
            "IF(parent_id=0,1,2) AS type\n" +
            "FROM\n" +
            "permission_menu LEFT JOIN menus on permission_menu.menu_id=menus.menu_id\n" +
            "UNION all\n" +
            "SELECT  \n" +
            "permission_id,\n" +
            "resource_name,\n" +
            "resource_url,\n" +
            "IF(menus.menu_id is null,0,menus.menu_id),\n" +
            "3 AS type\n" +
            "FROM\n" +
            "permission_resource LEFT JOIN resources ON resources.resource_id=permission_resource.resource_id\n" +
            "LEFT JOIN menu_resource on menu_resource.resource_id=permission_resource.permission_id\n" +
            "LEFT JOIN menus ON menus.menu_id=menu_resource.menu_id) temp\n" +
            "LEFT JOIN role_permission ON role_permission.permission_id=temp.permission_id\n" +
            "LEFT JOIN roles ON roles.role_id=role_permission.role_id\n" +
            "<where><if test=\"roleId!=null and roleId!=''\" > roles.role_id = #{roleId} </if></where>\n"+
            "</script>")
    List<AllResource> selectAllResourceByRoleId(@Param("roleId")Integer roleId);

/*    *//**
     * 查出所有的菜单资源信息
     * @return List<MenuResources>
     *//*
    @Select("<script>" +
            "SELECT mr.menu_id,r.resource_id,r.resource_name " +
            " FROM menu_resource mr LEFT JOIN resources r ON mr.resource_id = r.resource_id" +
            "</script>")
    List<MenuResources> listMenuResources();*/

}
