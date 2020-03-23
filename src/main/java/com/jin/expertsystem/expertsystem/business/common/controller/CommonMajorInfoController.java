package com.jin.expertsystem.expertsystem.business.common.controller;

import com.jin.expertsystem.expertsystem.business.common.model.MajorInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonMajorInfoService;
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
@Api(tags = "MajorInfo")
@RestController
@RequestMapping("/commonMajorInfoController/")
public class CommonMajorInfoController {

    @Autowired
    CommonMajorInfoService commonMajorInfoService;


    @ApiOperation(value = "新增")
    @PostMapping("insert")
    public Result insert(@RequestBody MajorInfo majorInfo) {
        return Result.result(commonMajorInfoService.insert(majorInfo),majorInfo.getId());
    }

    @ApiOperation(value = "根据实体中的属性删除")
    @DeleteMapping("delete")
    public Result delete(@RequestBody MajorInfo majorInfo) {
        return Result.result(commonMajorInfoService.delete(majorInfo),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return Result.result(commonMajorInfoService.deleteById(id),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键更新实体中存在的值")
    @PutMapping("updateById")
    public Result updateById(@RequestBody MajorInfo majorInfo) {
        return Result.result(commonMajorInfoService.updateById(majorInfo),"更新成功","更新失败");
    }

    @ApiOperation(value = "根据主键查找")
    @GetMapping("selectById/{id}")
    public Result selectById(@PathVariable String id) {
        return Result.result(commonMajorInfoService.selectById(id));
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("selectAll")
    public Result selectAll(@RequestParam(required = false) Integer status) {
        return Result.result(commonMajorInfoService.selectAllMajorInfo(status));
    }

    @ApiOperation(value = "分页查询所有")
    @GetMapping("selectAllForPage")
    public Result selectAllForPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String major) {
        PageUtils pageUtil = new PageUtils();
        pageUtil.setDataList(commonMajorInfoService.selectMajorInfoInfoByName(major));
        pageUtil.setCurrentPage(pageNum);
        pageUtil.setPageSizes(pageSize);
        return Result.result(pageUtil.paging());
    }
}
