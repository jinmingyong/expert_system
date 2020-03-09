package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonRoleUserDao;
import com.jin.expertsystem.expertsystem.business.common.model.RoleUser;
import com.jin.expertsystem.expertsystem.business.common.service.CommonRoleUserService;
import com.jin.expertsystem.expertsystem.business.common.need.AbstractMyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* @author JMY
* @date 2020/03/09
*/
@Service
public class CommonRoleUserServiceImpl extends AbstractMyService<RoleUser> implements CommonRoleUserService {

    @Autowired
    private CommonRoleUserDao commonRoleUserDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonRoleUserDao.selectAll());
    }

}
