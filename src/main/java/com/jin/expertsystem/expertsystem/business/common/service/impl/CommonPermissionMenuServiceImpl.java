package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonPermissionMenuDao;
import com.jin.expertsystem.expertsystem.business.common.model.PermissionMenu;
import com.jin.expertsystem.expertsystem.business.common.service.CommonPermissionMenuService;
import com.jin.expertsystem.expertsystem.business.common.need.AbstractMyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* @author JMY
* @date 2020/03/09
*/
@Service
public class CommonPermissionMenuServiceImpl extends AbstractMyService<PermissionMenu> implements CommonPermissionMenuService {

    @Autowired
    private CommonPermissionMenuDao commonPermissionMenuDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonPermissionMenuDao.selectAll());
    }

}
