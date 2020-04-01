package com.jin.expertsystem.expertsystem.business.expertExtraction.dao;

import com.jin.expertsystem.expertsystem.business.common.need.MyMapper;
import com.jin.expertsystem.expertsystem.business.expertExtraction.model.ExtractionResultDetailInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/4/1 15:42
 */
@Repository
public interface ResultDetailInfoDao extends MyMapper<ExtractionResultDetailInfo> {
    @Select("SELECT\n" +
            "result_detailed_info.id,\n" +
            "result_detailed_info.res_id,\n" +
            "result_detailed_info.exp_id,\n" +
            "result_detailed_info.flag_email,\n" +
            "result_detailed_info.flag_sts,\n" +
            "expert_info.`name`,\n" +
            "expert_info.age,\n" +
            "expert_info.sex,\n" +
            "expert_info.email,\n" +
            "expert_info.city,\n" +
            "company_info.company,\n" +
            "industry_info.industry,\n" +
            "jobgrade_info.job_grade,\n" +
            "major_info.major\n" +
            "FROM\n" +
            "result_detailed_info\n" +
            "LEFT JOIN expert_info ON result_detailed_info.exp_id = expert_info.expert_id\n" +
            "LEFT JOIN company_info ON expert_info.company = company_info.id\n" +
            "LEFT JOIN industry_info ON expert_info.industry = industry_info.id\n" +
            "LEFT JOIN jobgrade_info ON expert_info.job_grade = jobgrade_info.id\n" +
            "LEFT JOIN major_info ON expert_info.major = major_info.id\n" +
            "WHERE\n" +
            "result_detailed_info.res_id = #{resId}")
            List<ExtractionResultDetailInfo> selectByResId(@Param("resId") String resId);
}
