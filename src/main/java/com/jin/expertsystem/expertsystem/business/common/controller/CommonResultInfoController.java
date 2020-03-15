package com.jin.expertsystem.expertsystem.business.common.controller;

import com.jin.expertsystem.expertsystem.business.common.model.ResultInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonResultInfoService;
import com.jin.expertsystem.expertsystem.base.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.PageInfo;


/**
* @author JMY
* @date 2020/03/12
*/
@Api(tags = "ResultInfo")
@RestController
@RequestMapping("/commonResultInfoController/")
public class CommonResultInfoController {

    @Autowired
    CommonResultInfoService commonResultInfoService;


    @ApiOperation(value = "新增")
    @PostMapping("insert")
    public Result insert(@RequestBody ResultInfo resultInfo) {
        return Result.result(commonResultInfoService.insert(resultInfo),"新增成功","新增失败");
    }

    @ApiOperation(value = "根据实体中的属性删除")
    @DeleteMapping("delete")
    public Result delete(@RequestBody ResultInfo resultInfo) {
        return Result.result(commonResultInfoService.delete(resultInfo),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return Result.result(commonResultInfoService.deleteById(id),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键更新实体中存在的值")
    @PutMapping("updateById")
    public Result updateById(@RequestBody ResultInfo resultInfo) {
        return Result.result(commonResultInfoService.updateById(resultInfo),"更新成功","更新失败");
    }

    @ApiOperation(value = "根据主键查找")
    @GetMapping("selectById/{id}")
    public Result selectById(@PathVariable String id) {
        return Result.result(commonResultInfoService.selectById(id));
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("selectAll")
    public Result selectAll() {
        return Result.result(commonResultInfoService.selectAll());
    }

    @ApiOperation(value = "分页查询所有")
    @GetMapping("selectAllForPage")
    public PageInfo selectAllForPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return commonResultInfoService.selectAllForPage(pageNum,pageSize);
    }
}