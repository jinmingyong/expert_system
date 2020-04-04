package com.jin.expertsystem.expertsystem.business.personalManage.controller;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonUsersDao;
import com.jin.expertsystem.expertsystem.business.common.service.CommonUsersService;
import com.jin.expertsystem.expertsystem.business.personalManage.model.ReplyMessageParam;
import com.jin.expertsystem.expertsystem.business.personalManage.model.UpdatePasswordParams;
import com.jin.expertsystem.expertsystem.business.personalManage.service.PersonalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jinmingyong
 * @date 2020/3/23 18:33
 */
@Api(tags = "PersonalInfo")
@RestController
@RequestMapping("/personalInfoController/")
public class PersonalController {
    @Autowired
    private PersonalService personalService;

    @ApiOperation(value = "通过token获取用户信息", notes = "通过登录名获取用户信息")
    @GetMapping(value = "getUserInfoByToken")
    public Result getUserInfoByToken(HttpServletRequest request) {
        return Result.result(personalService.selectUserInfoByToken(request));
    }

    @ApiOperation(value = "通过token修改密码")
    @PutMapping(value = "updatePasswordByToken")
    public Result updatePasswordByToken(HttpServletRequest request, @RequestBody UpdatePasswordParams updatePasswordParams) {
        int i =personalService.updatePasswordByToken(request,updatePasswordParams);
        if (i==1){
            return Result.result(i,"修改成功");
        }else if (i==0){
            return Result.result(i,"修改成功","旧密码错误");
        }else {
            return Result.result(i,"修改成功","新密码与原来不可重复");
        }
    }

    @ApiOperation(value = "通过token获取用户消息信息")
    @GetMapping(value = "selectMessageByToken")
    public Result selectMessageByToken(HttpServletRequest request) {
        return Result.result(personalService.selectMessageByToken(request));
    }

    @ApiOperation(value = "通过token修改密码")
    @PutMapping(value = "replayMessage")
    public Result replayMessage( @RequestBody ReplyMessageParam replyMessageParam) {
        return Result.result(personalService.replayMessage(replyMessageParam),"回复成功","回复失败");
    }

    @ApiOperation(value = "获得用户是否有新消息")
    @GetMapping(value = "selectMessageCount")
    public Result selectMessageCount(HttpServletRequest request) {
        return Result.result(personalService.selectMessageCount(request));
    }
}
