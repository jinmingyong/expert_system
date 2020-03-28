package com.jin.expertsystem.expertsystem.business.sysmanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/25 18:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllResource {
    private Integer permissionId;
    private Integer roleId;
    private String name;
    private String url;
    private Integer parentId;
    private Integer type;
    private List<AllResource> children;
}
