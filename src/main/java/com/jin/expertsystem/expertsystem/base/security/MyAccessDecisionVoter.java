package com.jin.expertsystem.expertsystem.base.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

/**
 * @author GaoLiwei
 * @date 2019/5/8
 */
public class MyAccessDecisionVoter implements AccessDecisionVoter<Object> {
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> pathPermission) {
        //没有登陆
        if (authentication == null) {
            return ACCESS_DENIED;
        }
        int result = ACCESS_ABSTAIN;

        //获得用户拥有的权限
        Collection<? extends GrantedAuthority> userPermission = extractAuthorities(authentication);

        //遍历访问路径需要的权限
        for (ConfigAttribute path : pathPermission) {
            if (path.getAttribute() == null) {
                continue;
            }
            if (this.supports(path)) {
                result = ACCESS_DENIED;

                //判断是否不需要权限（路径的需要权限是permitAll时）
                if ("permitAll".equals(path.getAttribute())) {
                    return ACCESS_GRANTED;
                }

                //获得请求方法
                FilterInvocation filterInvocation = (FilterInvocation) o;
                String methonTyep = filterInvocation.getRequest().getMethod();
                //如果是OPTIONS请求，则不需要权限
                if ("OPTIONS".equals(methonTyep)){
                    return ACCESS_GRANTED;
                }


                //处理访问接口需要的权限
                String[] permissions = path.getAttribute().split(",");

                //判断用户是否拥有访问路径需要的权限
                for (GrantedAuthority authority : userPermission) {

                    for (String per : permissions) {
                        if (per.equals(authority.getAuthority())) {
                            return ACCESS_GRANTED;
                        }
                    }

                }
            }
        }

        return result;
    }

    /**
     * 得到用户权限
     * @param authentication
     * @return Collection<? extends GrantedAuthority>
     */
    Collection<? extends GrantedAuthority> extractAuthorities(
            Authentication authentication) {
        return authentication.getAuthorities();
    }


}
