package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonJobgradeInfoDao;
import com.jin.expertsystem.expertsystem.business.common.model.JobgradeInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonJobgradeInfoService;
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
public class CommonJobgradeInfoServiceImpl extends AbstractMyService<JobgradeInfo> implements CommonJobgradeInfoService {

    @Autowired
    private CommonJobgradeInfoDao commonJobgradeInfoDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonJobgradeInfoDao.selectAll());
    }

    @Override
    public List<JobgradeInfo> selectAllMajorInfo(Integer status) {
        return commonJobgradeInfoDao.selectAllJobgradeInfo(status);
    }

    @Override
    public List<JobgradeInfo> selectJobgradeInfoByName(String jobGrade) {
        return commonJobgradeInfoDao.selectAllJobgradeInfoByName(jobGrade);
    }

}
