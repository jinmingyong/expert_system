package com.jin.expertsystem.expertsystem.base.security;

import com.jin.expertsystem.expertsystem.business.sysmanagement.model.PathPermission;
import com.jin.expertsystem.expertsystem.utils.Utils;

import java.util.List;
import java.util.Map;

import static com.jin.expertsystem.expertsystem.base.security.MyFilterInvocationSecurityMetadataSource.urlPerMap;

/**
 * @author GaoLiwei
 * @date 2019/5/8
 */
public class SecurityUtil {

    /**
     * 设置访问接口需要的权限
     *
     * @param pathPermissionList
     */
    public static void setUrlPer(List<PathPermission> pathPermissionList) {

        //获得每个路径访问需要的权限
        Map<Object, List> pathPermissionMap = Utils.groupListByName(pathPermissionList, "resourceUrl");

        pathPermissionMap.forEach((k, v) -> {

            //这个路径需要的访问权限数量对比标志，当等于1的时候，则只需要配置一次即可；不等于1，则表示有2个以上，需要特殊配置
            int sizeFlag = 1;
            if (v.size() == sizeFlag) {
                List<PathPermission> perList = (List<PathPermission>) v;
                //将这个路径需要的唯一的权限配置好
                urlPerMap.put((String) k,perList.get(0).getPermissionCode() );
            } else {
                for (PathPermission per: (List<PathPermission>)v){
                    //获得当前路径在urlPerMap中的配置
                    String urlPer = urlPerMap.get(k);
                    //当urlPer为null的时候，表示是第一次对这个路径进行配置
                    if (null == urlPer){
                        //将这个路径需要的第一个权限配置好
                        urlPerMap.put((String) k, per.getPermissionCode());
                    }else{//当urlPer不为null的时候，表示已经对这个路径进行过配置，再次配置需用“,”拼接
                        urlPerMap.put((String) k, urlPer+","+per.getPermissionCode());
                    }
                }
            }

        });
    }

}
