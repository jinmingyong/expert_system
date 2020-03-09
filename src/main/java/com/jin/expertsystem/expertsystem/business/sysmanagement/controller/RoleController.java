package com.jin.expertsystem.expertsystem.business.sysmanagement.controller;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.sysmanagement.model.RoleParam;
import com.jin.expertsystem.expertsystem.business.sysmanagement.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JiChao on 2019/5/16.
 * 角色管理
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/roleController/")
public class RoleController {

    @Autowired
    RoleService roleServiceImpl;

    @ApiOperation(value = "通过角色id查询所拥有的权限")
    @GetMapping("selectPermByRoleId/{roleId}")
    public Result selectPermByRoleId(@PathVariable String roleId) {
        return Result.result(roleServiceImpl.selectPermByRoleId(roleId));
    }

    @ApiOperation(value = "修改角色的权限")
    @PutMapping("updatePermForRole")
    public Result updatePermForRole(@RequestBody RoleParam roleParam) {
        return Result.result(roleServiceImpl.updatePermForRole(roleParam),"修改成功","修改失败");
    }

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return roleServiceImpl.deleteRoleById(id);
    }

}
