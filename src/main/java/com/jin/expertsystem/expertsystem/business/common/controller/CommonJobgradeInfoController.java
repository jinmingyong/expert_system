package com.jin.expertsystem.expertsystem.business.common.controller;

import com.jin.expertsystem.expertsystem.business.common.model.JobgradeInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonJobgradeInfoService;
import com.jin.expertsystem.expertsystem.utils.PageUtils;
import com.jin.expertsystem.expertsystem.base.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.PageInfo;


/**
* @author JMY
* @date 2020/03/22
*/
@Api(tags = "JobgradeInfo")
@RestController
@RequestMapping("/commonJobgradeInfoController/")
public class CommonJobgradeInfoController {

    @Autowired
    CommonJobgradeInfoService commonJobgradeInfoService;


    @ApiOperation(value = "新增")
    @PostMapping("insert")
    public Result insert(@RequestBody JobgradeInfo jobgradeInfo) {
        return Result.result(commonJobgradeInfoService.insert(jobgradeInfo),jobgradeInfo.getId());
    }

    @ApiOperation(value = "根据实体中的属性删除")
    @DeleteMapping("delete")
    public Result delete(@RequestBody JobgradeInfo jobgradeInfo) {
        return Result.result(commonJobgradeInfoService.delete(jobgradeInfo),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return Result.result(commonJobgradeInfoService.deleteById(id),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键更新实体中存在的值")
    @PutMapping("updateById")
    public Result updateById(@RequestBody JobgradeInfo jobgradeInfo) {
        return Result.result(commonJobgradeInfoService.updateById(jobgradeInfo),"更新成功","更新失败");
    }

    @ApiOperation(value = "根据主键查找")
    @GetMapping("selectById/{id}")
    public Result selectById(@PathVariable String id) {
        return Result.result(commonJobgradeInfoService.selectById(id));
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("selectAll")
    public Result selectAll(@RequestParam(required = false) Integer status) {
        return Result.result(commonJobgradeInfoService.selectAllMajorInfo(status));
    }

    @ApiOperation(value = "分页查询所有")
    @GetMapping("selectAllForPage")
    public Result selectAllForPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String jobGrade) {
        PageUtils pageUtil = new PageUtils();
        pageUtil.setDataList(commonJobgradeInfoService.selectJobgradeInfoByName(jobGrade));
        pageUtil.setCurrentPage(pageNum);
        pageUtil.setPageSizes(pageSize);
        return Result.result(pageUtil.paging());
    }
}
