package com.jin.expertsystem.expertsystem.business.login.controller;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GaoLiwei
 * @date 2019/5/15
 */
@Api(tags = "登陆相关接口")
@RestController
@RequestMapping("/loginController/")
public class LoginController {

    @Value("${jwt.requestHeader}")
    private String requestHeader;
    @Autowired
    private LoginService loginService;


    @ApiOperation(value = "用户登陆", notes = "参数：登录名，密码")
    @PostMapping(value = "login")
    public Result createAuthenticationToken(@RequestBody Users user) throws AuthenticationException {
        return loginService.login(user.getUsername(), user.getPassword());
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping(value = "register")
    public Result register(@RequestBody Users user) {
        return loginService.register(user);
    }

    @ApiOperation(value = "用户登录刷新", notes = "用户登录刷新")
    @GetMapping(value = "refresh")
    public Result refreshAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(requestHeader);
        return loginService.refresh(token);
    }

    @ApiOperation(value = "根据权限查询菜单列表", notes = "根据权限查询菜单列表")
    @GetMapping(value = "queryMenuList")
    public Result queryMenuList(HttpServletRequest request) {
        return Result.result(loginService.queryMenuList(request));
    }

    @ApiOperation(value = "通过token获取用户信息", notes = "通过登录名获取用户信息")
    @GetMapping(value = "getUserInfoByToken")
    public Result getUserInfoByToken(HttpServletRequest request) {
        return Result.result(loginService.getUserInfoByToken(request));
    }
}
