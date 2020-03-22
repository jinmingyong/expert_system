package com.jin.expertsystem.expertsystem.business.common.service;

import com.jin.expertsystem.expertsystem.business.common.model.JobgradeInfo;
import com.jin.expertsystem.expertsystem.business.common.model.MajorInfo;
import com.jin.expertsystem.expertsystem.business.common.need.MyService;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
* @author JMY
* @date 2020/03/22
*/
public interface CommonJobgradeInfoService extends MyService<JobgradeInfo> {

    /**
    *  分页查询
    * @param pageNum
    * @param pageSize
    * @return PageInfo
    */
    PageInfo selectAllForPage(Integer pageNum, Integer pageSize);

    List<JobgradeInfo> selectAllMajorInfo(Integer status);

}
