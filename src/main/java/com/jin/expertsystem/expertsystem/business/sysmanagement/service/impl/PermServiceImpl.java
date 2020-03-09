package com.jin.expertsystem.expertsystem.business.sysmanagement.service.impl;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.base.security.SecurityUtil;
import com.jin.expertsystem.expertsystem.business.common.check.DeleteCheckService;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonPermissionMenuDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonPermissionResourceDao;
import com.jin.expertsystem.expertsystem.business.common.model.*;
import com.jin.expertsystem.expertsystem.business.common.service.CommonPermissionsService;
import com.jin.expertsystem.expertsystem.business.sysmanagement.dao.PermDao;
import com.jin.expertsystem.expertsystem.business.sysmanagement.dao.PermissionsDao;
import com.jin.expertsystem.expertsystem.business.sysmanagement.dao.ResourceDao;
import com.jin.expertsystem.expertsystem.business.sysmanagement.model.PathPermission;
import com.jin.expertsystem.expertsystem.business.sysmanagement.model.PermParam;
import com.jin.expertsystem.expertsystem.business.sysmanagement.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/5/16.
 */
@Service
public class PermServiceImpl implements PermService {

    @Autowired
    PermDao permDao;
    @Autowired
    CommonPermissionMenuDao commonPermissionMenuDao;
    @Autowired
    CommonPermissionResourceDao commonPermissionResourceDao;
    @Autowired
    DeleteCheckService deleteCheckService;
    @Autowired
    CommonPermissionsService commonPermissionsServiceImpl;
    @Autowired
    private PermissionsDao permissionsDao;
    @Autowired
    private ResourceDao resourceDao;

    @Override
    public List<Menus> selectMenuForPerm(Integer permissionId) {
        return permDao.selectMenuForPerm(permissionId);
    }

    @Override
    public List<Resources> selectResourceForPerm(Integer permissionId) {
        return permDao.selectResourceForPerm(permissionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMenuForPerm(PermParam permParam) {
        Integer permissionId = permParam.getPermissionId();
        List<Integer> menuList = permParam.getMenuIds();
        // 删除原来的
        PermissionMenu pm = new PermissionMenu();
        pm.setPermissionId(permissionId);
        commonPermissionMenuDao.delete(pm);
        // 添加新的
        List<PermissionMenu> saveList = new ArrayList<>();
        menuList.forEach(menuId -> {
            PermissionMenu permissionMenu = new PermissionMenu();
            permissionMenu.setMenuId(menuId);
            permissionMenu.setPermissionId(permissionId);
            saveList.add(permissionMenu);
        });
        return permDao.batchSaveMenu(saveList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateResourceForPerm(PermParam permParam) {
        Integer permissionId = permParam.getPermissionId();
        List<Integer> resourceList = permParam.getResourceIds();
        // 删除原来的
        PermissionResource pr = new PermissionResource();
        pr.setPermissionId(permissionId);
        commonPermissionResourceDao.delete(pr);
        // 添加新的
        List<PermissionResource> saveList = new ArrayList<>();
        resourceList.forEach(resourceId -> {
            PermissionResource permissionResource = new PermissionResource();
            permissionResource.setResourceId(resourceId);
            permissionResource.setPermissionId(permissionId);
            saveList.add(permissionResource);
        });
        int flag = permDao.batchSaveResource(saveList);
        if (flag > 0){

            //配置访问路径权限
            List<PathPermission> pathPermissionList = resourceDao.listPathPermission();
            SecurityUtil.setUrlPer(pathPermissionList);
        }
        return flag;
    }

    @Transactional
    @Override
    public Result deletePermById(Object id) {
        String successMsg = "删除权限成功";
        String failMsg = "删除权限失败";
        int operate = 0;
        boolean flag = deleteCheckService.checkPerm(id);
        if (flag) {
            // 删除关联菜单
            PermissionMenu permissionMenu = new PermissionMenu();
            permissionMenu.setPermissionId((Integer) id);
            commonPermissionMenuDao.delete(permissionMenu);
            // 删除关联资源
            PermissionResource permissionResource = new PermissionResource();
            permissionResource.setPermissionId((Integer) id);
            commonPermissionResourceDao.delete(permissionResource);
            operate = commonPermissionsServiceImpl.deleteById(id);
        } else {
            failMsg += "，系统中存在此权限的角色";
        }
        return Result.result(operate, successMsg, failMsg);
    }
}
