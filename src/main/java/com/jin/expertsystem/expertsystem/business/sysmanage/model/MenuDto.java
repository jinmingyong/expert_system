package com.jin.expertsystem.expertsystem.business.sysmanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
    private String menuName;

    /**
     * 菜单url
     */
    private String menuUrl;


    private Integer parentId;

    /**
     * 是否在左侧显示，1：显示，0：不显示，默认1
     */
    private Integer showStatus;

    /**
     * 显示顺序
     */
    private Integer displaySequence;

    /**
     * 子集菜单，可以为N级
     */
    List<MenuDto> menuDtoList;

    /**
     * 该菜单下对应的接口资源
     */
    List<ResourceDto> resourceDtoList;


}
