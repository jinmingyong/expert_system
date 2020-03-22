package com.jin.expertsystem.expertsystem.business.common.dao;

import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfotoShow;
import com.jin.expertsystem.expertsystem.business.common.need.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author JMY
* @date 2020/03/17
*/
@Repository
public interface CommonExpertInfoDao extends MyMapper<ExpertInfo> {

    @Select("<script>" +
            "SELECT\n" +
            "expert_info.expert_id,\n" +
            "expert_info.`name`,\n" +
            "expert_info.age,\n" +
            "expert_info.sex,\n" +
            "expert_info.birthday,\n" +
            "expert_info.nation,\n" +
            "expert_info.ic_card,\n" +
            "expert_info.job,\n" +
            "expert_info.working_year,\n" +
            "expert_info.degree,\n" +
            "expert_info.college,\n" +
            "expert_info.phone,\n" +
            "expert_info.tell_phone,\n" +
            "expert_info.email,\n" +
            "expert_info.city,\n" +
            "expert_info.time,\n" +
            "expert_info.`status`,\n" +
            "expert_info.estimate,\n" +
            "expert_info.picture,\n" +
            "jobgrade_info.job_grade,\n" +
            "industry_info.industry,\n" +
            "company_info.company,\n" +
            "major_info.major\n" +
            "FROM\n" +
            "expert_info\n" +
            "LEFT JOIN company_info ON expert_info.company = company_info.id\n" +
            "LEFT JOIN industry_info ON expert_info.industry = industry_info.id\n" +
            "LEFT JOIN jobgrade_info ON expert_info.job_grade = jobgrade_info.id\n" +
            "LEFT JOIN major_info ON expert_info.major = major_info.id\n"+
            "<where><if test=\"name!=null and name!=''\" > expert_info.`name` LIKE '%${name}%' </if></where>\n"+
            "</script>")
    List<ExpertInfotoShow> selectExpertInfoByName(@Param("name")String name);

    @Select("<script>" +
            "SELECT\n" +
            "expert_info.expert_id,\n" +
            "expert_info.`name`,\n" +
            "expert_info.age,\n" +
            "expert_info.sex,\n" +
            "expert_info.birthday,\n" +
            "expert_info.nation,\n" +
            "expert_info.ic_card,\n" +
            "expert_info.job,\n" +
            "expert_info.working_year,\n" +
            "expert_info.degree,\n" +
            "expert_info.college,\n" +
            "expert_info.phone,\n" +
            "expert_info.tell_phone,\n" +
            "expert_info.email,\n" +
            "expert_info.city,\n" +
            "expert_info.time,\n" +
            "expert_info.`status`,\n" +
            "expert_info.estimate,\n" +
            "expert_info.picture,\n" +
            "jobgrade_info.job_grade,\n" +
            "industry_info.industry,\n" +
            "company_info.company,\n" +
            "major_info.major\n" +
            "FROM\n" +
            "expert_info\n" +
            "LEFT JOIN company_info ON expert_info.company = company_info.id\n" +
            "LEFT JOIN industry_info ON expert_info.industry = industry_info.id\n" +
            "LEFT JOIN jobgrade_info ON expert_info.job_grade = jobgrade_info.id\n" +
            "LEFT JOIN major_info ON expert_info.major = major_info.id\n"+
            "WHERE\n" +
            "expert_info.expert_id = #{expertId}\n"+
            "</script>")
    ExpertInfo selectExpertInfoById(@Param("expertId")String expertId);

}
