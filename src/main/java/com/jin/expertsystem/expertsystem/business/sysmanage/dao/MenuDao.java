package com.jin.expertsystem.expertsystem.business.sysmanage.dao;

import com.jin.expertsystem.expertsystem.business.common.model.Menus;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.MenuDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/16 17:10
 */
@Repository
public interface MenuDao {

    @Select("<script>" +
            "SELECT\n" +
            "menus.menu_id,\n" +
            "menus.menu_name,\n" +
            "menus.menu_url,\n" +
            "menus.display_sequence,\n" +
            "menus.show_status,\n" +
            "menus.route_name,\n" +
            "menus.parent_id\n" +
            "FROM\n" +
            "role_user\n" +
            "LEFT JOIN role_permission ON role_permission.role_id = role_user.role_id\n" +
            "LEFT JOIN permission_menu ON role_permission.permission_id = permission_menu.permission_id\n" +
            "RIGHT JOIN menus ON permission_menu.menu_id = menus.menu_id\n" +
            "WHERE\n" +
            "role_user.user_id = #{userId}\n"+
            "</script>")
    @ResultType(Menus.class)
    List<Menus> selectMenuByUserId(@Param("userId") String userId);
}
