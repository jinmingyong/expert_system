package com.jin.expertsystem.expertsystem.business.sysmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GaoLiwei
 * @date 2019/5/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDto {

    /**
     * 资源Id
     */
    private Integer resourceId;

    /**
     * 资源名称
     */
    private String resourceName;

}
