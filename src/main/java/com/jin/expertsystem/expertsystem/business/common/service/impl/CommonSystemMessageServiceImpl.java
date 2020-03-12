package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonSystemMessageDao;
import com.jin.expertsystem.expertsystem.business.common.model.SystemMessage;
import com.jin.expertsystem.expertsystem.business.common.service.CommonSystemMessageService;
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
public class CommonSystemMessageServiceImpl extends AbstractMyService<SystemMessage> implements CommonSystemMessageService {

    @Autowired
    private CommonSystemMessageDao commonSystemMessageDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonSystemMessageDao.selectAll());
    }

}
