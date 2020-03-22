package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonMajorInfoDao;
import com.jin.expertsystem.expertsystem.business.common.model.MajorInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonMajorInfoService;
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
public class CommonMajorInfoServiceImpl extends AbstractMyService<MajorInfo> implements CommonMajorInfoService {

    @Autowired
    private CommonMajorInfoDao commonMajorInfoDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonMajorInfoDao.selectAll());
    }

    @Override
    public List<MajorInfo> selectAllMajorInfo(Integer status) {
        return commonMajorInfoDao.selectAllMajorInfo(status);
    }

}
