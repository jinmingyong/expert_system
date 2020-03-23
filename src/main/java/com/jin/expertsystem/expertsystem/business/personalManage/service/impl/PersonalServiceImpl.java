package com.jin.expertsystem.expertsystem.business.personalManage.service.impl;

import com.jin.expertsystem.expertsystem.base.jwt.JwtTokenUtil;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonUsersDao;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.personalManage.dao.PersonalDao;
import com.jin.expertsystem.expertsystem.business.personalManage.service.PersonalService;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jinmingyong
 * @date 2020/3/23 18:27
 */
@Service
public class PersonalServiceImpl implements PersonalService {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.requestHeader}")
    private String requestHeader;

    @Value("${jwt.tokenHeader}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PersonalDao personalDao;

    @Override
    public UserRoleInfo selectUserInfoByToken(HttpServletRequest request) {
        String token = request.getHeader(requestHeader);
        String realToken = token.substring(tokenHeader.length());
        String userId = jwtTokenUtil.getUserIdFromToken(realToken);
        return personalDao.selectUserInfoByToken(userId);
    }
}
