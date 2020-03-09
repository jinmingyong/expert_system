package com.jin.expertsystem.expertsystem.business.sysmanagement.controller;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.sysmanagement.model.PermParam;
import com.jin.expertsystem.expertsystem.business.sysmanagement.service.PermService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JiChao on 2019/5/16.
 * 权限管理
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/permController/")
public class PermController {

    @Autowired
    PermService permServiceImpl;

    /**
     * 查询权限下拥有的菜单
     * @param permissionId
     * @return
     */
    @ApiOperation(value = "查询权限下拥有的菜单")
    @GetMapping("selectMenuForPerm/{permissionId}")
    public Result selectMenuForPerm(@PathVariable Integer permissionId) {
        return Result.result(permServiceImpl.selectMenuForPerm(permissionId));
    }

    /**
     * 查询权限下拥有的资源
     * @param permissionId
     * @return
     */
    @ApiOperation(value = "查询权限下拥有的资源")
    @GetMapping("selectResourceForPerm/{permissionId}")
    public Result selectResourceForPerm(@PathVariable Integer permissionId) {
        return Result.result(permServiceImpl.selectResourceForPerm(permissionId));
    }

    /**
     * 修改权限下拥有的菜单
     * @return
     */
    @ApiOperation(value = "修改权限下拥有的菜单")
    @PutMapping("updateMenuForPerm")
    public Result updateMenuForPerm(@RequestBody PermParam permParam) {
        return Result.result(permServiceImpl.updateMenuForPerm(permParam),"修改成功","修改失败");
    }

    /**
     * 修改权限下拥有的资源
     * @return
     */
    @ApiOperation(value = "修改权限下拥有的资源")
    @PutMapping("updateResourceForPerm")
    public Result updateResourceForPerm(@RequestBody PermParam permParam) {
        return Result.result(permServiceImpl.updateResourceForPerm(permParam),"修改成功","修改失败");
    }

    @ApiOperation(value = "根据主键删除")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        return permServiceImpl.deletePermById(id);
    }

}
