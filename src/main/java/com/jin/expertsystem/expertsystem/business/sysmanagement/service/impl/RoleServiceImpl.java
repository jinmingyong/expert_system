package com.jin.expertsystem.expertsystem.business.sysmanagement.service.impl;

import com.jin.expertsystem.expertsystem.base.jwt.JwtTokenUtil;
import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.check.DeleteCheckService;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonRolePermissionDao;
import com.jin.expertsystem.expertsystem.business.common.model.Permissions;
import com.jin.expertsystem.expertsystem.business.common.model.RolePermission;
import com.jin.expertsystem.expertsystem.business.common.service.CommonRolesService;
import com.jin.expertsystem.expertsystem.business.sysmanagement.dao.RoleDao;
import com.jin.expertsystem.expertsystem.business.sysmanagement.dao.UserDao;
import com.jin.expertsystem.expertsystem.business.sysmanagement.model.RoleParam;
import com.jin.expertsystem.expertsystem.business.sysmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/5/16.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.requestHeader}")
    private String requestHeader;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    RoleDao roleDao;

    @Autowired
    CommonRolePermissionDao commonRolePermissionDao;

    @Autowired
    DeleteCheckService deleteCheckService;

    @Autowired
    CommonRolesService commonRolesServiceImpl;

    @Autowired
    UserDao userDao;


    @Override
    public List<Permissions> selectPermByRoleId(String roleId) {
        return roleDao.selectPermByRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updatePermForRole(RoleParam roleParam) {
        Integer roleId = roleParam.getRoleId();
        List<Integer> permList = roleParam.getPermIds();
        // 根据角色id删除中间表中的数据
        RolePermission rp = new RolePermission();
        rp.setRoleId(roleId);
        commonRolePermissionDao.delete(rp);
        // 批量添加 角色权限 中间表
        List<RolePermission> saveList = new ArrayList<>();
        permList.forEach(permId -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permId);
            saveList.add(rolePermission);
        });
        return roleDao.batchSave(saveList);
    }

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
            operate = commonRolesServiceImpl.deleteById(id);
        } else {
            failMsg += "，系统中存在此角色的用户";
        }
        return Result.result(operate, successMsg, failMsg);
    }
}
