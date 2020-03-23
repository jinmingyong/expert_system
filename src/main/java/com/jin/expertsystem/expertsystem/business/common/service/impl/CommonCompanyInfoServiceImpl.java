package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonCompanyInfoDao;
import com.jin.expertsystem.expertsystem.business.common.model.CompanyInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonCompanyInfoService;
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
public class CommonCompanyInfoServiceImpl extends AbstractMyService<CompanyInfo> implements CommonCompanyInfoService {

    @Autowired
    private CommonCompanyInfoDao commonCompanyInfoDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonCompanyInfoDao.selectAll());
    }

    @Override
    public List<CompanyInfo> selectAllMajorInfo(Integer status) {
        return commonCompanyInfoDao.selectAllCompanyInfo(status);
    }

    @Override
    public List<CompanyInfo> selectCompanyInfoByName(String company) {
        return commonCompanyInfoDao.selectAllCompanyInfoByName(company);
    }

}
