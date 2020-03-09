package com.jin.expertsystem.expertsystem.base.jwt;

import com.rcisoft.officesystem.business.common.model.Permissions;
import com.rcisoft.officesystem.business.common.model.Users;

import java.util.List;


/**
 *  工厂模式，创建jwtUser
 * @author GaoLiwei
 * @date 2019/4/15
 */
public final class JwtUserFactory {
    private JwtUserFactory() {
    }

    /**
     * 创建JwtUser工厂
     */
    public static JwtUser create(Users user, List<Permissions> permissionsList){
        return new JwtUser(
                user.getUserId(),
                user.getLoginName(),
                user.getPassword(),
                permissionsList
        );
    }


}
