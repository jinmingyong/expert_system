package com.jin.expertsystem.expertsystem.business.common.dao;

import com.jin.expertsystem.expertsystem.business.common.model.ResultDetailedInfo;
import com.jin.expertsystem.expertsystem.business.common.model.RolePermission;
import com.jin.expertsystem.expertsystem.business.common.need.MyMapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author JMY
* @date 2020/03/17
*/
@Repository
public interface CommonResultDetailedInfoDao extends MyMapper<ResultDetailedInfo> {
    @Insert("<script>" +
            "<foreach collection=\"list\" item=\"item\" separator=\";\">" +
            "insert into result_detailed_info(id, res_id, exp_id) values(#{item.id}, #{item.resId}, #{item.expId})" +
            "</foreach>" +
            "</script>")
    Integer batchSaveResultDetailed(List<ResultDetailedInfo> saveList);

}
