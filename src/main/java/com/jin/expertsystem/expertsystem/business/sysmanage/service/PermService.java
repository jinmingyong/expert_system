package com.jin.expertsystem.expertsystem.business.sysmanage.service;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.model.Menus;
import com.jin.expertsystem.expertsystem.business.common.model.Resources;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.PermParam;

import java.util.List;

/**
 * Created by JiChao on 2019/5/16.
 * 权限管理
 */

public interface PermService {

    /**
     * 查询权限下拥有的菜单
     * @param permissionId
     * @return
     */
    List<Menus> selectMenuForPerm(Integer permissionId);

    /**
     * 查询权限下拥有的资源
     * @param permissionId
     * @return
     */
    List<Resources> selectResourceForPerm(Integer permissionId);

    /**
     * 修改权限下拥有的菜单
     * @param permParam
     * @return
     */
    int updateMenuForPerm(PermParam permParam);

    /**
     * 修改权限下拥有的资源
     * @param permParam
     * @return
     */
    int updateResourceForPerm(PermParam permParam);

    /**
     * 根据id删除权限
     * @param id
     * @return
     */
    Result deletePermById(Object id);




}
