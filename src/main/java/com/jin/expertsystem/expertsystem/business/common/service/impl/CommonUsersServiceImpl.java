package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonUsersDao;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.common.need.AbstractMyService;
import com.jin.expertsystem.expertsystem.business.common.service.CommonUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author LiuYuanJun
* @date 2020/01/06
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

}
