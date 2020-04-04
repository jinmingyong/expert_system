package com.jin.expertsystem.expertsystem.business.expertExtraction.service;

import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import com.jin.expertsystem.expertsystem.business.expertExtraction.model.ExpertExtractionParam;
import com.jin.expertsystem.expertsystem.business.expertExtraction.model.ExtractionResultInfo;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.SendSmsParam;
import org.apache.ibatis.annotations.Param;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/30 13:15
 */
public interface ExpertExtractionService {
    // 按条件查询专家
    List<ExpertInfo> expertExtraction(ExpertExtractionParam expertExtractionParam);
    // 按条件随机抽取专家
    List<ExpertInfo> expertExtractionByRandom(ExpertExtractionParam expertExtractionParam);
    // 生成结果并发送email
    String sendSms(SendSmsParam sendSmsParam, HttpServletRequest request) throws MessagingException;
    // 查询所有结果
    List<ExtractionResultInfo> selectAllResult(String proName);
    // 根据id查询结果
    ExtractionResultInfo selectResultById(String resultId);
}
