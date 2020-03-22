package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonExpertInfoDao;
import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfotoShow;
import com.jin.expertsystem.expertsystem.business.common.service.CommonExpertInfoService;
import com.jin.expertsystem.expertsystem.business.common.need.AbstractMyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
* @author JMY
* @date 2020/03/17
*/
@Service
public class CommonExpertInfoServiceImpl extends AbstractMyService<ExpertInfo> implements CommonExpertInfoService {

    @Autowired
    private CommonExpertInfoDao commonExpertInfoDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonExpertInfoDao.selectAll());
    }

    @Override
    public List<ExpertInfotoShow> selectExpertInfoByName(String name) {
        return commonExpertInfoDao.selectExpertInfoByName(name);
    }

    @Override
    public ExpertInfo selectExpertInfoById(String expertId) {
        return commonExpertInfoDao.selectExpertInfoById(expertId);
    }

}
