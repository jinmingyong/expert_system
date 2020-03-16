package com.jin.expertsystem.expertsystem.business.sysmanage.service;

import com.jin.expertsystem.expertsystem.business.sysmanage.model.MenuDto;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserInfoAndMenu;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/5/16
 */
public interface MenuResourceManagementService {

    /**
     * 将所有菜单分级，并且查出每级菜单对应的接口，用于配置接口权限时显示
     *      flag为true时，查资源，flag为flase时，只查菜单，不查资源
     * @param flag
     * @return List<Menus>
     */
    List<MenuDto> listMenuResource(Boolean flag);

    /**
     * 更新接口权限配置
     * @return Integer
     */
    void updateUrlPer();

    /**
     * 查出用户信息以及拥有的菜单
     * @param token
     * @return UserInfoAndMenu
     */
    UserInfoAndMenu getUserInfoAndMenu(String token);

}
