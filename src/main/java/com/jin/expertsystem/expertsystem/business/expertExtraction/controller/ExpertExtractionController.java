package com.jin.expertsystem.expertsystem.business.expertExtraction.controller;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.expertExtraction.model.ExpertExtractionParam;
import com.jin.expertsystem.expertsystem.business.expertExtraction.service.ExpertExtractionService;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.SendSmsParam;
import com.jin.expertsystem.expertsystem.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jinmingyong
 * @date 2020/3/30 13:16
 */
@Api(tags = "专家抽取")
@RestController
@RequestMapping("/expertExtractionController/")
public class ExpertExtractionController {

    @Autowired
    private ExpertExtractionService expertExtractionService;

    @ApiOperation(value = "专家条件查询")
    @PostMapping(value = "expertExtraction")
    public Result expertExtraction(@RequestBody ExpertExtractionParam expertExtractionParam){
        PageUtils pageUtil = new PageUtils();
        pageUtil.setDataList(expertExtractionService.expertExtraction(expertExtractionParam));
        pageUtil.setCurrentPage(expertExtractionParam.getPageNum());
        pageUtil.setPageSizes(expertExtractionParam.getPageSize());
        return Result.result(pageUtil.paging());
    }

    @ApiOperation(value = "专家随机抽取")
    @PostMapping(value = "expertExtractionByRandom")
    public Result expertExtractionByRandom(@RequestBody ExpertExtractionParam expertExtractionParam){
        return Result.result(expertExtractionService.expertExtractionByRandom(expertExtractionParam));
    }

    @ApiOperation(value = "发送短信")
    @PostMapping(value = "sendSms")
    public Result sendSms(@RequestBody SendSmsParam sendSmsParam,HttpServletRequest request) throws MessagingException {
        return Result.result(expertExtractionService.sendSms(sendSmsParam,request));
    }

    @ApiOperation(value = "分页查询所有")
    @GetMapping("selectAllResult")
    public Result selectAllResult(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String proName) {
        PageUtils pageUtil = new PageUtils();
        pageUtil.setDataList(expertExtractionService.selectAllResult(proName));
        pageUtil.setCurrentPage(pageNum);
        pageUtil.setPageSizes(pageSize);
        return Result.result(pageUtil.paging());
    }

    @ApiOperation(value = "根据id查询结果")
    @GetMapping("selectResultById")
    public Result selectResultById(@RequestParam String resId) {
        return Result.result(expertExtractionService.selectResultById(resId));
    }

}
