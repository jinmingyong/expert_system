package com.jin.expertsystem.expertsystem.business.login.dao;

import com.jin.expertsystem.expertsystem.business.common.model.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hukaihan
 * @date 2019/7/1  10:11
 **/
@Repository
public interface MenuInfoDao {

    /**
     * 根据工号查询权限ids
     * @param userNumber
     * @return
     */
    @Select("<script>" +
            "SELECT p.permission_id\n" +
            "FROM users u,role_user ru, role_permission rp , permissions p\n" +
            " WHERE u.user_id = ru.user_id  and ru.role_id = rp.role_id  and  rp.permission_id = p.permission_id and u.login_name = #{userNumber}" +
            "</script>")
    List<Integer> listPermissions(@Param("userNumber") String userNumber);

    /**
     * 根据权限查询父级菜单节点
     * @param permIds
     * @return
     */
    @Select("select distinct m.* from permission_menu pu\n" +
            "left join menus m on m.menu_id = pu.menu_id\n" +
            "where pu.permission_id in (${perIds})\n" +
            "and m.parent_id = 0 and m.show_status = 1\n" +
            "order by m.display_sequence asc")
    List<Menus> queryFatherMenuList(@Param("perIds") String permIds);

    /**
     * 根据权限查询子级菜单节点
     * @param permIds
     * @return
     */
    @Select("select distinct m.* from permission_menu pu\n" +
            "left join menus m on m.menu_id = pu.menu_id\n" +
            "where pu.permission_id in (${perIds})\n" +
            "and m.parent_id != 0 and m.show_status = 1\n" +
            "order by m.display_sequence asc")
    List<Menus> queryChildMenuList(@Param("perIds") String permIds);
}
