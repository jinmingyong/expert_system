package com.jin.expertsystem.expertsystem.business.personalManage.controller;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonUsersDao;
import com.jin.expertsystem.expertsystem.business.common.service.CommonUsersService;
import com.jin.expertsystem.expertsystem.business.personalManage.service.PersonalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
