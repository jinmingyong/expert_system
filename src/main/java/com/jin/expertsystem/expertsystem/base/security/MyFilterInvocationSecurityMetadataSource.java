package com.jin.expertsystem.expertsystem.base.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GaoLiwei
 * @date 2019/5/8
 */
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public static Map<String, String> urlPerMap = new HashMap<>();


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
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
