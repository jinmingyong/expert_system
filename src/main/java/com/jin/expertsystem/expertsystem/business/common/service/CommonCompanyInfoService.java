package com.jin.expertsystem.expertsystem.business.common.service;

import com.jin.expertsystem.expertsystem.business.common.model.CompanyInfo;
import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfotoShow;
import com.jin.expertsystem.expertsystem.business.common.model.MajorInfo;
import com.jin.expertsystem.expertsystem.business.common.need.MyService;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
* @author JMY
* @date 2020/03/22
*/
public interface CommonCompanyInfoService extends MyService<CompanyInfo> {

    /**
    *  分页查询
    * @param pageNum
    * @param pageSize
    * @return PageInfo
    */
    PageInfo selectAllForPage(Integer pageNum, Integer pageSize);

    List<CompanyInfo> selectAllMajorInfo(Integer status);

    //模糊查询
    List<CompanyInfo> selectCompanyInfoByName(String company);

}
