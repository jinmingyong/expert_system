package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonUserDao;
import com.jin.expertsystem.expertsystem.business.common.model.User;
import com.jin.expertsystem.expertsystem.business.common.service.CommonUserService;
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
public class CommonUserServiceImpl extends AbstractMyService<User> implements CommonUserService {

    @Autowired
    private CommonUserDao commonUserDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonUserDao.selectAll());
    }

}
