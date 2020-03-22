package com.jin.expertsystem.expertsystem.business.common.service;

import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfotoShow;
import com.jin.expertsystem.expertsystem.business.common.need.MyService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
* @author JMY
* @date 2020/03/17
*/
public interface CommonExpertInfoService extends MyService<ExpertInfo> {

    /**
    *  分页查询
    * @param pageNum
    * @param pageSize
    * @return PageInfo
    */
    PageInfo selectAllForPage(Integer pageNum, Integer pageSize);

    //模糊查询
    List<ExpertInfotoShow> selectExpertInfoByName(String name);
    //根据id查询
    ExpertInfo selectExpertInfoById(String expertId);

}
