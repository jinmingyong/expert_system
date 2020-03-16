package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonPermissionResourceDao;
import com.jin.expertsystem.expertsystem.business.common.model.PermissionResource;
import com.jin.expertsystem.expertsystem.business.common.service.CommonPermissionResourceService;
import com.jin.expertsystem.expertsystem.business.common.need.AbstractMyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* @author JMY
* @date 2020/03/17
*/
@Service
public class CommonPermissionResourceServiceImpl extends AbstractMyService<PermissionResource> implements CommonPermissionResourceService {

    @Autowired
    private CommonPermissionResourceDao commonPermissionResourceDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonPermissionResourceDao.selectAll());
    }

}
