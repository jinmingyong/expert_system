package com.jin.expertsystem.expertsystem.business.sysmanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/6/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoAndMenu {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 菜单列表
     */
    List<MenuDto> menuDtoList;

}
