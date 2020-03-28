package com.jin.expertsystem.expertsystem.business.sysmanage.service.impl;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.check.DeleteCheckService;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonRolePermissionDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonRolesDao;
import com.jin.expertsystem.expertsystem.business.common.model.RolePermission;
import com.jin.expertsystem.expertsystem.business.common.model.Roles;
import com.jin.expertsystem.expertsystem.business.sysmanage.dao.ResourceDao;
import com.jin.expertsystem.expertsystem.business.sysmanage.dao.RoleDao;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.PathPermission;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.RoleAllResource;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.RoleParam;
import com.jin.expertsystem.expertsystem.business.sysmanage.service.RolesService;
import com.jin.expertsystem.expertsystem.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/24 22:41
 */
@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    private DeleteCheckService deleteCheckService;

    @Autowired
    private CommonRolePermissionDao commonRolePermissionDao;

    @Autowired
    private CommonRolesDao commonRolesDao;

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleDao roleDao;


    @Transactional
    @Override
    public Result deleteRoleById(Object id) {
        String successMsg = "删除角色成功";
        String failMsg = "删除角色失败";
        int operate = 0;
        boolean flag = deleteCheckService.checkRole(id);
        if (flag) {
            Example example = new Example(RolePermission.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("roleId", id);
            // 删除中间表
            commonRolePermissionDao.deleteByExample(example);
            // 删角色
            operate = commonRolesDao.deleteByPrimaryKey(id);
        } else {
            failMsg += "，系统中存在此角色的用户";
        }
        return Result.result(operate, successMsg, failMsg);
    }

    @Override
    public RoleAllResource deleteRolePermission(Integer roleId, Integer permissionId) {
        Example example = new Example(RolePermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId",roleId);
        criteria.andEqualTo("permissionId",permissionId);
        commonRolePermissionDao.deleteByExample(example);
        RoleAllResource roleAllResource=new RoleAllResource();
        Roles roles=commonRolesDao.selectByPrimaryKey(roleId);
        roleAllResource.setRoleId(roleId);
        roleAllResource.setRoleName(roles.getRoleName());
        roleAllResource.setRoleNumber(roles.getRoleNumber());
        roleAllResource.setAllResources(Utils.bulid(resourceDao.selectAllResourceByRoleId(roleId)));
        return roleAllResource;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateRolePermission(RoleParam roleParam) {
        List<Integer> perList=roleParam.getPermIds();
        // 删除原来的
        RolePermission rp=new RolePermission();
        rp.setRoleId(roleParam.getRoleId());
        commonRolePermissionDao.delete(rp);
        // 添加新的
        Integer roleId = roleParam.getRoleId();
        List<RolePermission> saveList = new ArrayList<>();
        perList.forEach(permissionId -> {
            RolePermission rolePermission=new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            saveList.add(rolePermission);
        });
        return roleDao.batchSavePermission(saveList);
    }

}
