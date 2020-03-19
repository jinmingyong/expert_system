package com.jin.expertsystem.expertsystem.business.common.controller;

import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonExpertInfoService;
import com.jin.expertsystem.expertsystem.base.result.Result;

import com.jin.expertsystem.expertsystem.utils.PageUtils;
import com.jin.expertsystem.expertsystem.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
* @author JMY
* @date 2020/03/17
*/
@Api(tags = "ExpertInfo")
@RestController
@RequestMapping("/commonExpertInfoController/")
public class CommonExpertInfoController {

    @Value("${picture.head.savePath}")
    String savePath;

    @Value("${picture.head.visitPath}")
    String visitPath;

    @Autowired
    CommonExpertInfoService commonExpertInfoService;


    @ApiOperation(value = "新增")
    @PostMapping("insert")
    public Result insert(@RequestBody ExpertInfo expertInfo) {
        expertInfo.setExpertId(Utils.getUUID());
        return Result.result(commonExpertInfoService.insert(expertInfo),"新增成功","新增失败");
    }

    @ApiOperation(value = "根据实体中的属性删除")
    @DeleteMapping("delete")
    public Result delete(@RequestBody ExpertInfo expertInfo) {
        return Result.result(commonExpertInfoService.delete(expertInfo),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return Result.result(commonExpertInfoService.deleteById(id),"删除成功","删除失败");
    }

    @ApiOperation(value = "根据主键更新实体中存在的值")
    @PutMapping("updateById")
    public Result updateById(@RequestBody ExpertInfo expertInfo) {
        return Result.result(commonExpertInfoService.updateById(expertInfo),"更新成功","更新失败");
    }

    @ApiOperation(value = "根据主键查找")
    @GetMapping("selectById/{id}")
    public Result selectById(@PathVariable String id) {
        return Result.result(commonExpertInfoService.selectById(id));
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("selectAll")
    public Result selectAll() {
        return Result.result(commonExpertInfoService.selectAll());
    }

    @ApiOperation(value = "模糊分页查询所有")
    @GetMapping("selectAllForPage")
    public Result selectAllForPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String name) {
        PageUtils pageUtil = new PageUtils();
        pageUtil.setDataList(commonExpertInfoService.selectExpertInfoByName(name));
        pageUtil.setCurrentPage(pageNum);
        pageUtil.setPageSizes(pageSize);
        return Result.result(pageUtil.paging());
    }

    @ApiOperation(value = "上传头像")
    @PostMapping("uploadHead")
    public Result uploadHead(@RequestBody MultipartFile upload)  {
        File file=new File(savePath);
        if (!file.exists()){
            file.mkdirs();
        }
        String filename = upload.getOriginalFilename();//获得名字
        filename= UUID.randomUUID().toString().replace("-","")+"_"+filename;
        try {
            upload.transferTo(new File(savePath + filename));
            return Result.result(visitPath+filename);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.result(0,filename);
        }
    }
}
