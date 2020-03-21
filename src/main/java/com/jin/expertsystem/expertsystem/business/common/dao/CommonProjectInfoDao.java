package com.jin.expertsystem.expertsystem.business.common.dao;

import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import com.jin.expertsystem.expertsystem.business.common.model.ProjectInfo;
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
public interface CommonProjectInfoDao extends MyMapper<ProjectInfo> {
    @Select("<script>" +
            "SELECT\n" +
            "*\n" +
            "FROM\n" +
            "project_info\n" +
            "<where><if test=\"name!=null and name!=''\" > project_info.`pro_name` LIKE '%${name}%' </if></where>\n"+
            "</script>")
    List<ProjectInfo> selectProjectInfoByName(@Param("name")String name);

}
