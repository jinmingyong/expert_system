package com.jin.expertsystem.expertsystem.business.common.controller;

import com.github.pagehelper.PageInfo;
import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.common.service.CommonUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @author LiuYuanJun
* @date 2020/01/06
*/
@Api(tags = "Users")
@RestController
@RequestMapping("/commonUsersController/")
public class CommonUsersController {

    @Autowired
    CommonUsersService commonUsersService;


    @ApiOperation(value = "新增")
    @PostMapping("insert")
    public Result insert(@RequestBody Users users) {
        return Result.result(commonUsersService.insert(users),"新增成功","新增失败");
    }

    @ApiOperation(value = "根据实体中的属性删除")
    @DeleteMapping("delete")
    public Result delete(@RequestBody Users users) {
        return Result.result(commonUsersService.delete(users),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return Result.result(commonUsersService.deleteById(id),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键更新实体中存在的值")
    @PutMapping("updateById")
    public Result updateById(@RequestBody Users users) {
        return Result.result(commonUsersService.updateById(users),"更新成功","更新失败");
    }

    @ApiOperation(value = "根据主键查找")
    @GetMapping("selectById/{id}")
    public Result selectById(@PathVariable String id) {
        return Result.result(commonUsersService.selectById(id));
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("selectAll")
    public Result selectAll() {
        return Result.result(commonUsersService.selectAll());
    }

    @ApiOperation(value = "分页查询所有")
    @GetMapping("selectAllForPage")
    public PageInfo selectAllForPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return commonUsersService.selectAllForPage(pageNum,pageSize);
    }
}