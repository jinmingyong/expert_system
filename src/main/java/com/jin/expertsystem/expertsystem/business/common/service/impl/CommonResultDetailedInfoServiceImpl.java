package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonResultDetailedInfoDao;
import com.jin.expertsystem.expertsystem.business.common.model.ResultDetailedInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonResultDetailedInfoService;
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
public class CommonResultDetailedInfoServiceImpl extends AbstractMyService<ResultDetailedInfo> implements CommonResultDetailedInfoService {

    @Autowired
    private CommonResultDetailedInfoDao commonResultDetailedInfoDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonResultDetailedInfoDao.selectAll());
    }

}
