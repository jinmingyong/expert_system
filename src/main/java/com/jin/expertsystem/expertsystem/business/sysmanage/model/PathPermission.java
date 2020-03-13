package com.jin.expertsystem.expertsystem.business.sysmanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源Id与权限编码
 * @author GaoLiwei
 * @date 2019/5/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PathPermission {
    /**
     * 资源路径
     */
    String resourceUrl;

    /**
     * 权限编码
     */
    String permissionCode;
}
