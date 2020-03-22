package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonIndustryInfoDao;
import com.jin.expertsystem.expertsystem.business.common.model.IndustryInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonIndustryInfoService;
import com.jin.expertsystem.expertsystem.business.common.need.AbstractMyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
* @author JMY
* @date 2020/03/22
*/
@Service
public class CommonIndustryInfoServiceImpl extends AbstractMyService<IndustryInfo> implements CommonIndustryInfoService {

    @Autowired
    private CommonIndustryInfoDao commonIndustryInfoDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonIndustryInfoDao.selectAll());
    }

    @Override
    public List<IndustryInfo> selectAllMajorInfo(Integer status) {
        return commonIndustryInfoDao.selectAllIndustryInfo(status);
    }

}
