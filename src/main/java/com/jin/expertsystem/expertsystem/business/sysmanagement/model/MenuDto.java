package com.jin.expertsystem.expertsystem.business.sysmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/5/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    /**
     * 菜单Id
     */
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String meunName;

    /**
     * 子集菜单，可以为N级
     */
    List<MenuDto> menuDtoList;

    /**
     * 该菜单下对应的接口资源
     */
    List<ResourceDto> resourceDtoList;


}
