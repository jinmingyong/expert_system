package com.jin.expertsystem.expertsystem.business.expertExtraction.dao;

import com.jin.expertsystem.expertsystem.business.common.need.MyMapper;
import com.jin.expertsystem.expertsystem.business.expertExtraction.model.ExtractionResultInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/4/1 15:19
 */
@Repository
public interface ResultInfoDao extends MyMapper<ExtractionResultInfo> {
    @Select("<script>" +
            "SELECT\n" +
            "result_info.result_id,\n" +
            "result_info.pro_id,\n" +
            "result_info.type,\n" +
            "project_info.pro_name,\n" +
            "project_info.create_time,\n" +
            "project_info.extract_num,\n" +
            "project_info.more_ext_num\n" +
            "FROM\n" +
            "result_info\n" +
            "LEFT JOIN project_info ON result_info.pro_id = project_info.project_id\n"+
            "<where>" +
            "<if test=\"proName!=null and proName!=''\" > and project_info.pro_name LIKE '%${proName}%' </if>\n"+
            "</where>"+
            "</script>")
    @Results({
            @Result(id=true,column = "result_id",property = "resultId"),
            @Result(column="result_id",property="children",
                    many=@Many(
                            select="com.jin.expertsystem.expertsystem.business.expertExtraction.dao.ResultDetailInfoDao.selectByResId",
                            fetchType= FetchType.LAZY))
    })
    List<ExtractionResultInfo> selectAllResult(@Param("proName")String proName);


    @Select("<script>" +
            "SELECT\n" +
            "result_info.result_id,\n" +
            "result_info.pro_id,\n" +
            "result_info.type,\n" +
            "project_info.pro_name,\n" +
            "project_info.create_time,\n" +
            "project_info.extract_num,\n" +
            "project_info.more_ext_num\n" +
            "FROM\n" +
            "result_info\n" +
            "LEFT JOIN project_info ON result_info.pro_id = project_info.project_id\n"+
            "where\n" +
            "result_info.result_id = #{resultId}\n"+
            "</script>")
    @Results({
            @Result(id=true,column = "result_id",property = "resultId"),
            @Result(column="result_id",property="children",
                    many=@Many(
                            select="com.jin.expertsystem.expertsystem.business.expertExtraction.dao.ResultDetailInfoDao.selectByResId",
                            fetchType= FetchType.LAZY))
    })
    ExtractionResultInfo selectResultById(@Param("resultId")String resultId);

}
