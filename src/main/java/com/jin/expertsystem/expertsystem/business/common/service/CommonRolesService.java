package com.jin.expertsystem.expertsystem.business.common.service;

import com.jin.expertsystem.expertsystem.business.common.model.Roles;
import com.jin.expertsystem.expertsystem.business.common.need.MyService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author JMY
* @date 2020/03/17
*/
public interface CommonRolesService extends MyService<Roles> {

    /**
    *  分页查询
    * @param pageNum
    * @param pageSize
    * @return PageInfo
    */
    PageInfo selectAllForPage(Integer pageNum, Integer pageSize);

    List<Roles> selectAllRolesInfoByName(String roleName);

}
