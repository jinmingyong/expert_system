package com.jin.expertsystem.expertsystem.business.common.dao;

import com.jin.expertsystem.expertsystem.business.common.model.MessageManage;
import com.jin.expertsystem.expertsystem.business.common.model.ResultDetailedInfo;
import com.jin.expertsystem.expertsystem.business.common.need.MyMapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author JMY
* @date 2020/03/17
*/
@Repository
public interface CommonMessageManageDao extends MyMapper<MessageManage> {

    @Insert("<script>" +
            "<foreach collection=\"list\" item=\"item\" separator=\";\">" +
            "insert into message_manage(mes_id, exp_id,mes_content, sender_id, mes_title, res_id, status) values(#{item.mes_id}, #{item.exp_id}, #{item.mes_content}, #{item.sender_id}, #{item.mes_title}, #{item.res_id},, #{item.status} )" +
            "</foreach>" +
            "</script>")
    Integer batchSaveMessageManage(List<MessageManage> saveList);

}