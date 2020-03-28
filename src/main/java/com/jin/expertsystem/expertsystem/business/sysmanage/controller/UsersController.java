package com.jin.expertsystem.expertsystem.business.sysmanage.controller;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.model.CompanyInfo;
import com.jin.expertsystem.expertsystem.business.common.model.RoleUser;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.sysmanage.service.UsersService;
import com.jin.expertsystem.expertsystem.business.sysmanage.service.impl.UsersServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jinmingyong
 * @date 2020/3/24 20:13
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/usersController/")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "根据名字查询工会")
    @PostMapping("selectUsersByUsername")
    public Result selectUsersByUsername(@RequestBody Users users) {
        return Result.result(usersService.selectUsersByUsername(users));
    }

    @ApiOperation(value = "新增")
    @PostMapping("insertUsersInfo")
    public Result insertUsersInfo(@RequestBody Users users) {
        return Result.result(usersService.insertUsersInfo(users),"添加成功","添加失败");
    }

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return Result.result(usersService.deleteById(id),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键删除")
    @PutMapping("updateUserRole")
    public Result updateUserRole(@RequestBody RoleUser roleUser) {
        return Result.result(usersService.updateUserRole(roleUser),"更新成功","更新失败");
    }

}
