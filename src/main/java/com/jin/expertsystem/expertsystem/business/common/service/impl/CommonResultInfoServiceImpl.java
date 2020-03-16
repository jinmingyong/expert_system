package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonResultInfoDao;
import com.jin.expertsystem.expertsystem.business.common.model.ResultInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonResultInfoService;
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
public class CommonResultInfoServiceImpl extends AbstractMyService<ResultInfo> implements CommonResultInfoService {

    @Autowired
    private CommonResultInfoDao commonResultInfoDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonResultInfoDao.selectAll());
    }

}
