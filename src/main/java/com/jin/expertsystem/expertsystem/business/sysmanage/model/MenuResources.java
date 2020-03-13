package com.jin.expertsystem.expertsystem.business.sysmanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单对应的资源
 * @author GaoLiwei
 * @date 2019/5/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuResources {

    /**
     * 菜单Id
     */
    private Integer menuId;

    /**
     * 资源Id
     */
    private Integer resourceId;

    /**
     * 资源名称
     */
    private String resourceName;
}
