package com.jin.expertsystem.expertsystem.base.security;

import com.jin.expertsystem.expertsystem.business.sysmanage.dao.ResourceDao;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.PathPermission;
import io.jsonwebtoken.impl.JwtMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jinmingyong
 * @date 2020/3/14 22:04
 */
//当访问一个url时返回这个url所需要的访问权限。
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    public static Map<String,String> urlPerMap=new HashMap<>();

    private final AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        FilterInvocation fi = (FilterInvocation) o;
        String url = fi.getRequestUrl();
        //处理？拼接的参数
        String flag = "?";
        if (url.contains(flag)){
            url = url.substring(0,url.indexOf(flag));
        }
        for (Map.Entry<String, String> entry : urlPerMap.entrySet()) {
            if (antPathMatcher.match(entry.getKey(), url)) {
                return SecurityConfig.createList(entry.getValue());
            }
        }

        //没有匹配到,默认是要登录才能访问
        return SecurityConfig.createList("ROLE_USER");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
