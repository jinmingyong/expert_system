package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonMenuResourceDao;
import com.jin.expertsystem.expertsystem.business.common.model.MenuResource;
import com.jin.expertsystem.expertsystem.business.common.service.CommonMenuResourceService;
import com.jin.expertsystem.expertsystem.business.common.need.AbstractMyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* @author JMY
* @date 2020/03/25
*/
@Service
public class CommonMenuResourceServiceImpl extends AbstractMyService<MenuResource> implements CommonMenuResourceService {

    @Autowired
    private CommonMenuResourceDao commonMenuResourceDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonMenuResourceDao.selectAll());
    }

}
