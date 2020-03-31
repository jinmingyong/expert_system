package com.jin.expertsystem.expertsystem.business.expertExtraction.dao;

import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/30 13:14
 */
@Repository
public interface ExpertExtractionDao {

    @Select("<script>" +
            "SELECT\n" +
            "*\n" +
            "FROM\n" +
            "expert_info\n"+
            "where status = '1' \n" +
            "<if test=\"!cityList.isEmpty() \">" +
            "and city in\n" +
            "<foreach collection=\"cityList\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">" +
            "#{item}" +
            "</foreach>"+
            " </if>" +
            "<if test=\" !jobGradeList.isEmpty() \">" +
            "and  job_grade in\n" +
            "<foreach collection=\"jobGradeList\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">\n" +
            "#{item}\n" +
            "</foreach>"+
            " </if>" +
            "<if test=\" !industryList.isEmpty() \"> " +
            "and  industry in\n" +
            "<foreach collection=\"industryList\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">\n" +
            "#{item}\n" +
            "</foreach>"+
            " </if>" +
            "<if test=\" !companyList.isEmpty()\"> " +
            "and  company in\n" +
            "<foreach collection=\"companyList\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">\n" +
            "#{item}\n" +
            "</foreach>"+
            " </if>" +
            "<if test=\" !majorList.isEmpty() \"> " +
            "and  major in\n" +
            "<foreach collection=\"majorList\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">\n" +
            "#{item}\n" +
            "</foreach>"+
            " </if>" +
            "<if test=\"name!=null and name!=''\" > and expert_info.`name` LIKE '%${name}%' </if>\n"+
            "</script>"
    )
    List<ExpertInfo> expertExtraction(@Param("cityList") List<String> cityList,
                                      @Param("jobGradeList") List<Integer> jobGradeList,
                                      @Param("industryList") List<Integer> industryList,
                                      @Param("companyList") List<Integer> companyList,
                                      @Param("majorList") List<Integer> majorList,
                                      @Param("name") String name);
}
