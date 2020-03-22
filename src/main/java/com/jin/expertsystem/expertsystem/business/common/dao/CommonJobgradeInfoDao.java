package com.jin.expertsystem.expertsystem.business.common.dao;

import com.jin.expertsystem.expertsystem.business.common.model.CompanyInfo;
import com.jin.expertsystem.expertsystem.business.common.model.JobgradeInfo;
import com.jin.expertsystem.expertsystem.business.common.need.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author JMY
* @date 2020/03/22
*/
@Repository
public interface CommonJobgradeInfoDao extends MyMapper<JobgradeInfo> {
    @Select("<script>" +
            "SELECT\n" +
            "*\n"+
            "FROM\n" +
            "jobgrade_info\n" +
            "<where><if test=\"status!=null and status!=''\" > `status` = 1 </if></where>\n"+
            "</script>")
    List<JobgradeInfo> selectAllJobgradeInfo(@Param("status")Integer status);

}
