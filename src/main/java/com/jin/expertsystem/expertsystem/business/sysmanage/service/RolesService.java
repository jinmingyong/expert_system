package com.jin.expertsystem.expertsystem.business.sysmanage.service;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.model.RolePermission;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.RoleAllResource;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.RoleParam;

import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/24 22:41
 */
public interface RolesService {
    Result deleteRoleById(Object id);


    RoleAllResource deleteRolePermission(Integer roleId, Integer permissionId);

    Integer updateRolePermission(RoleParam roleParam);


}
