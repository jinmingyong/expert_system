package com.jin.expertsystem.expertsystem.business.expertExtraction.service;

import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import com.jin.expertsystem.expertsystem.business.expertExtraction.model.ExpertExtractionParam;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.SendSmsParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/30 13:15
 */
public interface ExpertExtractionService {
    List<ExpertInfo> expertExtraction(ExpertExtractionParam expertExtractionParam);
    List<ExpertInfo> expertExtractionByRandom(ExpertExtractionParam expertExtractionParam);
    Integer sendSms(SendSmsParam sendSmsParam);
}
