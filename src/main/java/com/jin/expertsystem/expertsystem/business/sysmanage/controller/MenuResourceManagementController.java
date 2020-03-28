package com.jin.expertsystem.expertsystem.business.sysmanage.controller;

import com.alibaba.fastjson.JSONArray;
import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.AllResource;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.PathPermission;
import com.jin.expertsystem.expertsystem.business.sysmanage.service.MenuResourceManagementService;
import com.jin.expertsystem.expertsystem.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/5/16
 */
@Api(tags ="菜单资源管理")
@RestController
@RequestMapping("/menuResourceManagementController/")
public class MenuResourceManagementController {

    @Value("${jwt.requestHeader}")
    private String requestHeader;
    @Autowired
    private MenuResourceManagementService menuResourceManagementService;

    @ApiOperation(value = "查看所有菜单以及对应资源", notes = "是否查询资源标志位")
    @GetMapping(value = "listMenuResource/{flag}")
    public Result listMenuResource(@PathVariable Boolean flag){
        return Result.result(menuResourceManagementService.listMenuResource(flag));
    }


    @ApiOperation(value = "查询用户下拥有的菜单", notes = "参数：无")
    @PostMapping(value = "getUserInfoAndMenu")
    public Result getUserInfoAndMenu(HttpServletRequest request){
        String token = request.getHeader(requestHeader);
        return Result.result(menuResourceManagementService.getUserInfoAndMenu(token));
    }

    @ApiOperation(value = "更新接口权限", notes = "需要先在数据库中填好数据")
    @PutMapping("updateUrlPer")
    public Result updateUrlPer(){
        menuResourceManagementService.updateUrlPer();
        return Result.result("更新配置成功");
    }

    @ApiOperation(value = "查询所有资源")
    @GetMapping(value = "selectAllResource")
    public Result selectAllResource(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String resourceName){
        PageUtils pageUtil = new PageUtils();
        pageUtil.setDataList(menuResourceManagementService.selectAllResource(resourceName));
        pageUtil.setCurrentPage(pageNum);
        pageUtil.setPageSizes(pageSize);
        return Result.result(pageUtil.paging());
    }

    @ApiOperation(value = "查询列表菜单所有资源树形结构")
    @GetMapping(value = "selectAllResourceForTree")
    public Result selectAllResourceForTree(){
        return Result.result(menuResourceManagementService.selectAllResourceForTree());
    }


    @ApiOperation(value = "查询所有资源树形结构")
    @GetMapping(value = "allResourceForTree")
    public Result allResourceForTree(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String roleName){
        PageUtils pageUtil = new PageUtils();
        pageUtil.setDataList(menuResourceManagementService.allResourceForTree(roleName));
        pageUtil.setCurrentPage(pageNum);
        pageUtil.setPageSizes(pageSize);
        return Result.result(pageUtil.paging());
    }


}
