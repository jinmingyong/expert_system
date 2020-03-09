package com.jin.expertsystem.expertsystem.business.sysmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by JiChao on 2019/5/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleParam {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id集合
     */
    private List<Integer> permIds;

}
