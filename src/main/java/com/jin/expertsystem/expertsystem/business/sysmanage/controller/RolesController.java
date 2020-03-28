package com.jin.expertsystem.expertsystem.business.sysmanage.controller;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.RoleParam;
import com.jin.expertsystem.expertsystem.business.sysmanage.service.RolesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jinmingyong
 * @date 2020/3/24 22:39
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/roleController/")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return rolesService.deleteRoleById(id);
    }

    @ApiOperation(value = "删除权限")
    @GetMapping("deleteRolePermission")
    public Result deleteRolePermission(@RequestParam Integer roleId,@RequestParam Integer permissionId) {
        return Result.result(rolesService.deleteRolePermission(roleId,permissionId));
    }

    @ApiOperation(value = "更新角色权限")
    @PutMapping("updateRolePermission")
    public Result updateRolePermission(@RequestBody RoleParam roleParam) {
        return Result.result(rolesService.updateRolePermission(roleParam));
    }
}
