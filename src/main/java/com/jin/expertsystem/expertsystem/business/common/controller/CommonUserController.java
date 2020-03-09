package com.jin.expertsystem.expertsystem.business.common.controller;

import com.jin.expertsystem.expertsystem.business.common.model.User;
import com.jin.expertsystem.expertsystem.business.common.service.CommonUserService;
import com.jin.expertsystem.expertsystem.base.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.PageInfo;


/**
* @author JMY
* @date 2020/03/09
*/
@Api(tags = "User")
@RestController
@RequestMapping("/commonUserController/")
public class CommonUserController {

    @Autowired
    CommonUserService commonUserService;


    @ApiOperation(value = "新增")
    @PostMapping("insert")
    public Result insert(@RequestBody User user) {
        return Result.result(commonUserService.insert(user),"新增成功","新增失败");
    }

    @ApiOperation(value = "根据实体中的属性删除")
    @DeleteMapping("delete")
    public Result delete(@RequestBody User user) {
        return Result.result(commonUserService.delete(user),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return Result.result(commonUserService.deleteById(id),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键更新实体中存在的值")
    @PutMapping("updateById")
    public Result updateById(@RequestBody User user) {
        return Result.result(commonUserService.updateById(user),"更新成功","更新失败");
    }

    @ApiOperation(value = "根据主键查找")
    @GetMapping("selectById/{id}")
    public Result selectById(@PathVariable String id) {
        return Result.result(commonUserService.selectById(id));
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("selectAll")
    public Result selectAll() {
        return Result.result(commonUserService.selectAll());
    }

    @ApiOperation(value = "分页查询所有")
    @GetMapping("selectAllForPage")
    public PageInfo selectAllForPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return commonUserService.selectAllForPage(pageNum,pageSize);
    }
}
