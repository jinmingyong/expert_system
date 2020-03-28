package com.jin.expertsystem.expertsystem.business.sysmanage.service.impl;

import com.jin.expertsystem.expertsystem.base.jwt.JwtTokenUtil;
import com.jin.expertsystem.expertsystem.base.security.MyFilterInvocationSecurityMetadataSource;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonMenusDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonRolesDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonUsersDao;
import com.jin.expertsystem.expertsystem.business.common.model.Menus;
import com.jin.expertsystem.expertsystem.business.common.model.Roles;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.sysmanage.dao.MenuDao;
import com.jin.expertsystem.expertsystem.business.sysmanage.dao.ResourceDao;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.*;
import com.jin.expertsystem.expertsystem.business.sysmanage.service.MenuResourceManagementService;
import com.jin.expertsystem.expertsystem.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GaoLiwei
 * @date 2019/5/16
 */
@Service
public class MenuResourceManagementServiceImpl implements MenuResourceManagementService {

    @Value("${jwt.tokenHeader}")
    private String tokenHead;
    @Autowired
    private CommonMenusDao commonMenusDao;
    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CommonUsersDao commonUsersDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private CommonRolesDao commonRolesDao;





    @Override
    public List<MenuDto> listMenuResource(Boolean flag) {
        //返回的结果
        List<MenuDto> returnMenuDtoList = new ArrayList<>();
        //对菜单以及资源进行处理
        setMenu(returnMenuDtoList,flag,null);
        return returnMenuDtoList;
    }


    @Override
    public void updateUrlPer() {
        //配置访问路径权限
        List<PathPermission> pathPermissionList = resourceDao.listPathPermission();
        for (PathPermission p:pathPermissionList
        ) {
            MyFilterInvocationSecurityMetadataSource.urlPerMap.put(p.getResourceUrl(),p.getPermissionCode());
        }
    }

    @Override
    public UserInfoAndMenu getUserInfoAndMenu(String token) {
        //要返回的数据
        UserInfoAndMenu returnUserInfoAndMenu = new UserInfoAndMenu();

        //获得token
        String tokenPlayload = token.substring(tokenHead.length());
        //获得工号
        String userId = jwtTokenUtil.getUserIdFromToken(tokenPlayload);
        //获得当前用户的信息
        Users useUser = new Users();
        useUser.setUsername(userId);
        Users user = commonUsersDao.selectByPrimaryKey(userId);
        //处理菜单信息
        List<MenuDto> menuDtoList = new ArrayList<>();
        setMenu(menuDtoList,false,userId);

        //封装信息
        returnUserInfoAndMenu.setUserName(user.getUsername());
        returnUserInfoAndMenu.setMenuDtoList(menuDtoList);

        return returnUserInfoAndMenu;
    }

    @Override
    public List<AllResource> selectAllResource(String resourceName) {
        return resourceDao.selectAllResource(resourceName);
    }


    @Override
    public List<AllResource> selectAllResourceForTree() {
        return Utils.bulid(resourceDao.selectAllResource(null));
    }
    @Override
    public List<RoleAllResource> allResourceForTree(String roleName) {
        List<RoleAllResource> roleAllResources=new ArrayList<>();
        List<AllResource> list=resourceDao.selectAllResourceByTree(roleName);
        List<Roles> roles=commonRolesDao.selectAllRolesInfoByName(roleName);
        for (Roles r:roles
             ) {
            RoleAllResource roleAllResource=new RoleAllResource();
            List<AllResource> fa=new ArrayList<>();
            roleAllResource.setRoleId(r.getRoleId());
            roleAllResource.setRoleName(r.getRoleName());
            roleAllResource.setRoleNumber(r.getRoleNumber());
            for (AllResource a:list){
                if (a.getRoleId()==r.getRoleId()){
                    fa.add(a);
                }
            }
            fa= Utils.bulid(fa);
            roleAllResource.setAllResources(fa);
            roleAllResources.add(roleAllResource);
        }
        return roleAllResources;
    }


    /**
     * 处理主菜单
     */
    private void setMenu(List<MenuDto> returnMenuDtoList, Boolean flag,String userId){
        List<Menus> menusList;
        //查出所有菜单
        if (userId != null) {
          menusList = menuDao.selectMenuByUserId(userId);
        }else {
            menusList =commonMenusDao.selectAll();
        }
        Map<Object, List> menuResourcesListGroup = new HashMap<>();

        //将所有最上级菜单信息分类
        for (Menus menus : menusList) {
            if (menus.getParentId() == 0) {
                MenuDto menuDto = new MenuDto();
                menuDto.setMenuId(menus.getMenuId());
                menuDto.setMenuName(menus.getMenuName());
                menuDto.setShowStatus(menus.getShowStatus());
                returnMenuDtoList.add(menuDto);
            }
        }

        //判断是否封装资源信息
        /*if (flag){
            //查出所有菜单资源信息
            List<MenuResources> menuResourcesList = resourceDao.listMenuResources();
            //按照菜单进行分组
            menuResourcesListGroup = Utils.groupListByName(menuResourcesList, "menuId");

            for (MenuDto m : returnMenuDtoList) {

                //封装当前最上级菜单的资源信息
                List<ResourceDto> returnResourceDtoList = new ArrayList<>();

                //获得当前最上级菜单的资源信息
                List<MenuResources> list = menuResourcesListGroup.get(m.getMenuId());

                //对每个最上级菜单资源进行封装
                setResource(returnResourceDtoList, list);

                m.setResourceDtoList(returnResourceDtoList);

            }
        }*/

        setMenuResource(returnMenuDtoList, menuResourcesListGroup, menusList,flag);
    }
    /**
     * 对子集菜单以及他的资源进行处理
     *
     * @author GaoLiWei
     * @date 14:21 2019/5/16
     **/

    private void setMenuResource(List<MenuDto> returnMenuDtoList, Map<Object, List> menuResourcesListGroup, List<Menus> menusList, Boolean flag) {

        //分别处理每一个最上级菜单，封装他的子菜单信息
        for (MenuDto fatherMenu : returnMenuDtoList) {
            //遍历所有菜单，查看该菜单是否属于当前菜单的子菜单
            for (Menus sonMenu : menusList) {

                //如果当前菜单属于这个菜单的子菜单
                if (sonMenu.getParentId().equals(fatherMenu.getMenuId())) {
                    //封装当前菜单的信息
                    List<MenuDto> sonMenuDtoList = new ArrayList<>();
                    List<ResourceDto> returnResourceDtoList = new ArrayList<>();

                    //设置子菜单的菜单Id与菜单名称
                    MenuDto sonMenuDto = new MenuDto();
                    sonMenuDto.setMenuId(sonMenu.getMenuId());
                    sonMenuDto.setMenuName(sonMenu.getMenuName());
                    sonMenuDto.setShowStatus(sonMenu.getShowStatus());
                    sonMenuDto.setDisplaySequence(sonMenu.getDisplaySequence());
                    sonMenuDto.setMenuUrl(sonMenu.getMenuUrl());
                   /* if (flag){
                        //获得该子菜单拥有的资源
                        List<MenuResources> list = menuResourcesListGroup.get(sonMenu.getMenuId());
                        //对该子菜单资源进行封装
                        setResource(returnResourceDtoList, list);
                        sonMenuDto.setResourceDtoList(returnResourceDtoList);
                    }*/

                    //将封装好的子菜单信息设置到父菜单信息中
                    if (fatherMenu.getMenuDtoList() == null){
                        sonMenuDtoList.add(sonMenuDto);
                        fatherMenu.setMenuDtoList(sonMenuDtoList);
                    }else{
                        fatherMenu.getMenuDtoList().add(sonMenuDto);
                    }

                    //递归，继续查看该菜单下是否还拥有子菜单
                    setMenuResource(sonMenuDtoList, menuResourcesListGroup, menusList,flag);
                }
            }
        }
    }

    /**
     * 封装当前菜单拥有的资源
     *
     * @author GaoLiWei
     * @date 15:02 2019/5/16
     **/
    private void setResource(List<ResourceDto> returnResourceDtoList, List<MenuResources> list) {
        if (null != list) {
            for (MenuResources menuResources : list) {
                ResourceDto resourceDto = new ResourceDto();
                resourceDto.setResourceId(menuResources.getResourceId());
                resourceDto.setResourceName(menuResources.getResourceName());
                returnResourceDtoList.add(resourceDto);
            }
        }
    }


}
