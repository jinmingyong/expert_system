package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonProjectInfoDao;
import com.jin.expertsystem.expertsystem.business.common.model.ProjectInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonProjectInfoService;
import com.jin.expertsystem.expertsystem.business.common.need.AbstractMyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* @author JMY
* @date 2020/03/12
*/
@Service
public class CommonProjectInfoServiceImpl extends AbstractMyService<ProjectInfo> implements CommonProjectInfoService {

    @Autowired
    private CommonProjectInfoDao commonProjectInfoDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonProjectInfoDao.selectAll());
    }

}
