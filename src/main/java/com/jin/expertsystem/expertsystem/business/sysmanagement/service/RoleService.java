package com.jin.expertsystem.expertsystem.business.sysmanagement.service;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.model.Permissions;
import com.jin.expertsystem.expertsystem.business.sysmanagement.model.RoleParam;

import java.util.List;

/**
 * Created by JiChao on 2019/5/16.
 * 角色管理
 */
public interface RoleService {

    /**
     * 通过角色id查询所拥有的权限
     * @param roleId
     * @return
     */
    List<Permissions> selectPermByRoleId(String roleId);

    /**
     * 修改角色的权限
     * @param roleParam
     * @return
     */
    int updatePermForRole(RoleParam roleParam);

    /**
     * 根据主键删除角色
     * @param id
     * @return
     */
    Result deleteRoleById(Object id);

}
