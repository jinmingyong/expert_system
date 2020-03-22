package com.jin.expertsystem.expertsystem.base.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author jinmingyong
 * @date 2020/3/14 23:03
 */
//该方法决定该权限是否有权限访问该资源，其实object就是一个资源的地址，authentication是当前用户的
// 对应权限，如果没登陆就为游客，登陆了就是该用户对应的权限
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> pathPermission) throws AccessDeniedException, InsufficientAuthenticationException {
        if(pathPermission == null) {
            return;
        }
        System.out.println(o.toString()); // object is a URL.
        //所请求的资源拥有的权限(一个资源对多个权限)
        Iterator<ConfigAttribute> iterator = pathPermission.iterator();
        while(iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            //访问所请求资源所需要的权限
            String needPermission = configAttribute.getAttribute();
            System.out.println("访问"+o.toString()+"需要的权限是：" + needPermission);
            //用户所拥有的权限authentication
            //获得请求方法
            FilterInvocation filterInvocation = (FilterInvocation) o;
            String methonTyep = filterInvocation.getRequest().getMethod();
            if ("ROLE_ANONYMOUS".equals(needPermission)) {
                return ;
            }
            //如果是OPTIONS请求，则不需要权限
            if ("OPTIONS".equals(methonTyep)){
                return ;
            }
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for(GrantedAuthority ga : authorities) {
                if(needPermission.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        //没有权限
        throw new AccessDeniedException(" 没有权限访问！ ");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
