package com.jin.expertsystem.expertsystem.business.sysmanage.dao;

import com.jin.expertsystem.expertsystem.business.common.model.Resources;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.MenuResources;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.PathPermission;
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

    /**
     * 查出所有的菜单资源信息
     * @return List<MenuResources>
     */
    @Select("<script>" +
            "SELECT mr.menu_id,r.resource_id,r.resource_name " +
            " FROM menu_resource mr LEFT JOIN resources r ON mr.resource_id = r.resource_id" +
            "</script>")
    List<MenuResources> listMenuResources();
}
