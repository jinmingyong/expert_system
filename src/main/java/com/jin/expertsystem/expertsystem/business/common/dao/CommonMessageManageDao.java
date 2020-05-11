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
            "insert into message_manage(mes_id, exp_id,mes_content, sender_id, mes_title, res_id, status) values(#{item.mesId}, #{item.expId}, #{item.mesContent}, #{item.senderId}, #{item.mesTitle}, #{item.resId}, #{item.status} )" +
            "</foreach>" +
            "</script>")
    Integer batchSaveMessageManage(List<MessageManage> saveList);

}