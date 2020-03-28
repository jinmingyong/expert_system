package com.jin.expertsystem.expertsystem.business.sysmanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/27 15:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAllResource {
    private Integer roleId;
    private String roleName;
    private String roleNumber;
    private List<AllResource> allResources;
}
