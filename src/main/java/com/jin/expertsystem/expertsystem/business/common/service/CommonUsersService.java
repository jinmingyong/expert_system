package com.jin.expertsystem.expertsystem.business.common.service;

import com.github.pagehelper.PageInfo;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.common.need.MyService;


/**
* @author LiuYuanJun
* @date 2020/01/06
*/
public interface CommonUsersService extends MyService<Users> {

    /**
    *  分页查询
    * @param pageNum
    * @param pageSize
    * @return PageInfo
    */
    PageInfo selectAllForPage(Integer pageNum, Integer pageSize);

}
