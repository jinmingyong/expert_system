package com.jin.expertsystem.expertsystem.business.common.controller;

import com.jin.expertsystem.expertsystem.business.common.model.ResultDetailedInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonResultDetailedInfoService;
import com.jin.expertsystem.expertsystem.business.common.utils.PageUtils;
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
@Api(tags = "ResultDetailedInfo")
@RestController
@RequestMapping("/commonResultDetailedInfoController/")
public class CommonResultDetailedInfoController {

    @Autowired
    CommonResultDetailedInfoService commonResultDetailedInfoService;


    @ApiOperation(value = "新增")
    @PostMapping("insert")
    public Result insert(@RequestBody ResultDetailedInfo resultDetailedInfo) {
        return Result.result(commonResultDetailedInfoService.insert(resultDetailedInfo),"新增成功","新增失败");
    }

    @ApiOperation(value = "根据实体中的属性删除")
    @DeleteMapping("delete")
    public Result delete(@RequestBody ResultDetailedInfo resultDetailedInfo) {
        return Result.result(commonResultDetailedInfoService.delete(resultDetailedInfo),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return Result.result(commonResultDetailedInfoService.deleteById(id),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键更新实体中存在的值")
    @PutMapping("updateById")
    public Result updateById(@RequestBody ResultDetailedInfo resultDetailedInfo) {
        return Result.result(commonResultDetailedInfoService.updateById(resultDetailedInfo),"更新成功","更新失败");
    }

    @ApiOperation(value = "根据主键查找")
    @GetMapping("selectById/{id}")
    public Result selectById(@PathVariable String id) {
        return Result.result(commonResultDetailedInfoService.selectById(id));
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("selectAll")
    public Result selectAll() {
        return Result.result(commonResultDetailedInfoService.selectAll());
    }

    @ApiOperation(value = "分页查询所有")
    @GetMapping("selectAllForPage")
    public Result selectAllForPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageUtils pageUtil = new PageUtils();
        pageUtil.setDataList(commonResultDetailedInfoService.selectAll());
        pageUtil.setCurrentPage(pageNum);
        pageUtil.setPageSizes(pageSize);
        return Result.result(pageUtil.paging());
    }
}
