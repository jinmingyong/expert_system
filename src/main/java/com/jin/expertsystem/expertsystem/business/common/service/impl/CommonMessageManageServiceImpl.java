package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonMessageManageDao;
import com.jin.expertsystem.expertsystem.business.common.model.MessageManage;
import com.jin.expertsystem.expertsystem.business.common.service.CommonMessageManageService;
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
public class CommonMessageManageServiceImpl extends AbstractMyService<MessageManage> implements CommonMessageManageService {

    @Autowired
    private CommonMessageManageDao commonMessageManageDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonMessageManageDao.selectAll());
    }

}
