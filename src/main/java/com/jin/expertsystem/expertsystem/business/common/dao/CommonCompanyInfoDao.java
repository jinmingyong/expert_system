package com.jin.expertsystem.expertsystem.business.common.dao;

import com.jin.expertsystem.expertsystem.business.common.model.CompanyInfo;
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
public interface CommonCompanyInfoDao extends MyMapper<CompanyInfo> {

    @Select("<script>" +
            "SELECT\n" +
            "*\n"+
            "FROM\n" +
            "company_info\n" +
            "<where><if test=\"status!=null and status!=''\" > company_info.`status` = 1 </if></where>\n"+
            "</script>")
    List<CompanyInfo> selectAllCompanyInfo(@Param("status")Integer status);

    @Select("<script>" +
            "SELECT\n" +
            "*\n"+
            "FROM\n" +
            "company_info\n" +
            "<where><if test=\"company!=null and company!=''\" > company  LIKE '%${company}%' </if></where>\n"+
            "</script>")
    List<CompanyInfo> selectAllCompanyInfoByName(@Param("company") String company);



}
