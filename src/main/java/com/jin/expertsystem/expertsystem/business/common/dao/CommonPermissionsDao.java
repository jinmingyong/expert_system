package com.jin.expertsystem.expertsystem.business.common.dao;

import com.jin.expertsystem.expertsystem.business.common.model.Permissions;
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
public interface CommonPermissionsDao extends MyMapper<Permissions> {

    @Select("<script>" +
            "SELECT\n" +
            "*\n"+
            "FROM\n" +
            "permissions\n" +
            "<where><if test=\"permissionsName!=null and permissionsName!=''\" > permission_name  LIKE '%${permissionsName}%' </if></where>\n"+
            "</script>")
    List<Permissions> selectAllPermissionsInfoByName(@Param("permissionsName") String permissionsName);


}
