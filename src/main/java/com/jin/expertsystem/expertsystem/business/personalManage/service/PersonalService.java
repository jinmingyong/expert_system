package com.jin.expertsystem.expertsystem.business.personalManage.service;

import com.jin.expertsystem.expertsystem.business.common.model.MessageManage;
import com.jin.expertsystem.expertsystem.business.personalManage.model.ReplyMessageParam;
import com.jin.expertsystem.expertsystem.business.personalManage.model.UpdatePasswordParams;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/23 18:26
 */
public interface PersonalService {
    //获得用户信息
    UserRoleInfo selectUserInfoByToken(HttpServletRequest request);
    // 修改密码
    int updatePasswordByToken(HttpServletRequest request, UpdatePasswordParams updatePasswordParams);
    // 获得消息列表
    List<MessageManage> selectMessageByToken(HttpServletRequest request);

    int replayMessage(ReplyMessageParam replyMessageParam);
    // 查看是否有需要回复的消息
    Integer selectMessageCount(HttpServletRequest request);
}
