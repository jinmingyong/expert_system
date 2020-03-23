package com.jin.expertsystem.expertsystem.business.common.service;

import com.jin.expertsystem.expertsystem.business.common.model.CompanyInfo;
import com.jin.expertsystem.expertsystem.business.common.model.IndustryInfo;
import com.jin.expertsystem.expertsystem.business.common.model.MajorInfo;
import com.jin.expertsystem.expertsystem.business.common.need.MyService;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
* @author JMY
* @date 2020/03/22
*/
public interface CommonIndustryInfoService extends MyService<IndustryInfo> {

    /**
    *  分页查询
    * @param pageNum
    * @param pageSize
    * @return PageInfo
    */
    PageInfo selectAllForPage(Integer pageNum, Integer pageSize);

    List<IndustryInfo> selectAllMajorInfo(Integer status);

    List<IndustryInfo> selectIndustryInfoByName(String industry);

}
