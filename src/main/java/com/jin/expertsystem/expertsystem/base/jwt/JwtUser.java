package com.jin.expertsystem.expertsystem.base.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jin.expertsystem.expertsystem.business.common.model.Permissions;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/4/15
 */
@Data
public class JwtUser implements UserDetails {


    private static final long serialVersionUID = -4070913768662945949L;

    /**
     * 用户id
     */
    private final String userId;

    /**
     * 工号
     */
    private final String loginName;

    /**
     * 用户密码
     */
    private final String password;

    /**
     * 用户权限
     */
    private final List<Permissions> permissionsList;


    public JwtUser(String userId, String loginName, String password,  List<Permissions> permissionsList) {
        this.userId = userId;
        this.loginName = loginName;
        this.password = password;
        this.permissionsList = permissionsList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> lists = new ArrayList<>();
        for (Permissions permissions : permissionsList) {
            lists.add(new SimpleGrantedAuthority(permissions.getPermissionCode()));
        }
        return lists;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginName;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


}
