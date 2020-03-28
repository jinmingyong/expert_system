package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonUsersDao;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.common.service.CommonUsersService;
import com.jin.expertsystem.expertsystem.business.common.need.AbstractMyService;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
* @author JMY
* @date 2020/03/17
*/
@Service
public class CommonUsersServiceImpl extends AbstractMyService<Users> implements CommonUsersService {

    @Autowired
    private CommonUsersDao commonUsersDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonUsersDao.selectAll());
    }

    @Override
    public List<UserRoleInfo> selectAllCompanyInfoByName(String username) {
        return commonUsersDao.selectAllCompanyInfoByName(username);
    }

}
