package com.jin.expertsystem.expertsystem.business.common.controller;

import com.jin.expertsystem.expertsystem.business.common.model.IndustryInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonIndustryInfoService;
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
@Api(tags = "IndustryInfo")
@RestController
@RequestMapping("/commonIndustryInfoController/")
public class CommonIndustryInfoController {

    @Autowired
    CommonIndustryInfoService commonIndustryInfoService;


    @ApiOperation(value = "新增")
    @PostMapping("insert")
    public Result insert(@RequestBody IndustryInfo industryInfo) {
        return Result.result(commonIndustryInfoService.insert(industryInfo),industryInfo.getId());
    }

    @ApiOperation(value = "根据实体中的属性删除")
    @DeleteMapping("delete")
    public Result delete(@RequestBody IndustryInfo industryInfo) {
        return Result.result(commonIndustryInfoService.delete(industryInfo),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return Result.result(commonIndustryInfoService.deleteById(id),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键更新实体中存在的值")
    @PutMapping("updateById")
    public Result updateById(@RequestBody IndustryInfo industryInfo) {
        return Result.result(commonIndustryInfoService.updateById(industryInfo),"更新成功","更新失败");
    }

    @ApiOperation(value = "根据主键查找")
    @GetMapping("selectById/{id}")
    public Result selectById(@PathVariable String id) {
        return Result.result(commonIndustryInfoService.selectById(id));
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("selectAll")
    public Result selectAll(@RequestParam(required = false) Integer status) {
        return Result.result(commonIndustryInfoService.selectAllMajorInfo(status));
    }

    @ApiOperation(value = "分页查询所有")
    @GetMapping("selectAllForPage")
    public Result selectAllForPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageUtils pageUtil = new PageUtils();
        pageUtil.setDataList(commonIndustryInfoService.selectAll());
        pageUtil.setCurrentPage(pageNum);
        pageUtil.setPageSizes(pageSize);
        return Result.result(pageUtil.paging());
    }
}
