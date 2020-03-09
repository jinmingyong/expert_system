package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonResourcesDao;
import com.jin.expertsystem.expertsystem.business.common.model.Resources;
import com.jin.expertsystem.expertsystem.business.common.service.CommonResourcesService;
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
public class CommonResourcesServiceImpl extends AbstractMyService<Resources> implements CommonResourcesService {

    @Autowired
    private CommonResourcesDao commonResourcesDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonResourcesDao.selectAll());
    }

}
