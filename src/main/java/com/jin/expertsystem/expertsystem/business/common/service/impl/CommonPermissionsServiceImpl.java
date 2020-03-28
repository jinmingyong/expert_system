package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonPermissionsDao;
import com.jin.expertsystem.expertsystem.business.common.model.Permissions;
import com.jin.expertsystem.expertsystem.business.common.service.CommonPermissionsService;
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
public class CommonPermissionsServiceImpl extends AbstractMyService<Permissions> implements CommonPermissionsService {

    @Autowired
    private CommonPermissionsDao commonPermissionsDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonPermissionsDao.selectAll());
    }

    @Override
    public List<Permissions> selectAllPermissionsInfoByName(String permissionsName) {
        return commonPermissionsDao.selectAllPermissionsInfoByName(permissionsName);
    }

}
