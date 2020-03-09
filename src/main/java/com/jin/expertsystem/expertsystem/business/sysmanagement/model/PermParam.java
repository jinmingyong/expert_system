package com.jin.expertsystem.expertsystem.business.sysmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by JiChao on 2019/5/16.
 * 权限管理--参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermParam {

    /**
     * 权限id
     */
    private Integer permissionId;

    /**
     * 菜单id集合
     */
    private List<Integer> menuIds;

    /**
     * 资源id集合
     */
    private List<Integer> resourceIds;

}
