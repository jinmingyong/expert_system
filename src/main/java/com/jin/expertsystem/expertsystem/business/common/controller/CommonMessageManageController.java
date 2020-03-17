package com.jin.expertsystem.expertsystem.business.common.controller;

import com.jin.expertsystem.expertsystem.business.common.model.MessageManage;
import com.jin.expertsystem.expertsystem.business.common.service.CommonMessageManageService;
import com.jin.expertsystem.expertsystem.utils.PageUtils;
import com.jin.expertsystem.expertsystem.base.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.PageInfo;


/**
* @author JMY
* @date 2020/03/17
*/
@Api(tags = "MessageManage")
@RestController
@RequestMapping("/commonMessageManageController/")
public class CommonMessageManageController {

    @Autowired
    CommonMessageManageService commonMessageManageService;


    @ApiOperation(value = "新增")
    @PostMapping("insert")
    public Result insert(@RequestBody MessageManage messageManage) {
        return Result.result(commonMessageManageService.insert(messageManage),"新增成功","新增失败");
    }

    @ApiOperation(value = "根据实体中的属性删除")
    @DeleteMapping("delete")
    public Result delete(@RequestBody MessageManage messageManage) {
        return Result.result(commonMessageManageService.delete(messageManage),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return Result.result(commonMessageManageService.deleteById(id),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键更新实体中存在的值")
    @PutMapping("updateById")
    public Result updateById(@RequestBody MessageManage messageManage) {
        return Result.result(commonMessageManageService.updateById(messageManage),"更新成功","更新失败");
    }

    @ApiOperation(value = "根据主键查找")
    @GetMapping("selectById/{id}")
    public Result selectById(@PathVariable String id) {
        return Result.result(commonMessageManageService.selectById(id));
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("selectAll")
    public Result selectAll() {
        return Result.result(commonMessageManageService.selectAll());
    }

    @ApiOperation(value = "分页查询所有")
    @GetMapping("selectAllForPage")
    public Result selectAllForPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageUtils pageUtil = new PageUtils();
        pageUtil.setDataList(commonMessageManageService.selectAll());
        pageUtil.setCurrentPage(pageNum);
        pageUtil.setPageSizes(pageSize);
        return Result.result(pageUtil.paging());
    }
}
