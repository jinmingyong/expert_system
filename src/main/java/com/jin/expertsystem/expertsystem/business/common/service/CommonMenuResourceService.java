package com.jin.expertsystem.expertsystem.business.common.service;

import com.jin.expertsystem.expertsystem.business.common.model.MenuResource;
import com.jin.expertsystem.expertsystem.business.common.need.MyService;
import com.github.pagehelper.PageInfo;


/**
* @author JMY
* @date 2020/03/25
*/
public interface CommonMenuResourceService extends MyService<MenuResource> {

    /**
    *  分页查询
    * @param pageNum
    * @param pageSize
    * @return PageInfo
    */
    PageInfo selectAllForPage(Integer pageNum, Integer pageSize);

}