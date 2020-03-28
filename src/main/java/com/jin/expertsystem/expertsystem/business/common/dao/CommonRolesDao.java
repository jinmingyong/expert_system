package com.jin.expertsystem.expertsystem.business.common.dao;

import com.jin.expertsystem.expertsystem.business.common.model.CompanyInfo;
import com.jin.expertsystem.expertsystem.business.common.model.Roles;
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
public interface CommonRolesDao extends MyMapper<Roles> {

    @Select("<script>" +
            "SELECT\n" +
            "*\n"+
            "FROM\n" +
            "roles\n" +
            "<where><if test=\"roleName!=null and roleName!=''\" > role_name  LIKE '%${roleName}%' </if></where>\n"+
            "</script>")
    List<Roles> selectAllRolesInfoByName(@Param("roleName") String roleName);

}
