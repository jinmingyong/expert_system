package com.jin.expertsystem.expertsystem.business.expertExtraction.service;

import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import com.jin.expertsystem.expertsystem.business.expertExtraction.model.ExpertExtractionParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/30 13:15
 */
public interface ExpertExtractionService {
    List<ExpertInfo> expertExtraction(ExpertExtractionParam expertExtractionParam);
    List<ExpertInfo> expertExtractionByRandom(ExpertExtractionParam expertExtractionParam);
}
