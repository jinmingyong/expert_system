package com.jin.expertsystem.expertsystem.business.login.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GaoLiwei
 * @date 2019/4/17
 */
@Api(tags = "MyTestController")
@RestController
@RequestMapping("/myTest/")
public class MyTestController {

    @ApiOperation(value = "ROLE_ADMIN角色")
    @PostMapping(value = "testRoleAdmin")
    public String testRoleAdmin() {
        return "ROLE_ADMIN角色";
    }

    @ApiOperation(value = "ROLE_USER角色")
    @PostMapping(value = "testRoleUser")
    public String testRoleUser() {
        return "ROLE_USER角色";
    }

    @ApiOperation(value = "GET权限")
    @PostMapping(value = "testGet")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String testGet() {
        return "GET权限";
    }

    @ApiOperation(value = "DELETE权限")
    @PostMapping(value = "testDelete")
    public String testDelete() {
        return "DELETE权限";
    }

    @ApiOperation(value = "PUT权限")
    @PostMapping(value = "testPut")
    public String testPut() {
        return "PUT权限";
    }

}
