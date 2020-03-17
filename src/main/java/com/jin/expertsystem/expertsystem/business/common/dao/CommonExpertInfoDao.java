package com.jin.expertsystem.expertsystem.business.common.dao;

import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
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
            "*\n" +
            "FROM\n" +
            "expert_info\n" +
            "<where><if test=\"name!=null and name!=''\" > expert_info.`name` LIKE '%${name}%' </if></where>\n"+
            "</script>")
    List<ExpertInfo> selectExpertInfoByName(@Param("name")String name);

}
