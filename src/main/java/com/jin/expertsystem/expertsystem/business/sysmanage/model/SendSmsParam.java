package com.jin.expertsystem.expertsystem.business.sysmanage.model;

import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import com.jin.expertsystem.expertsystem.business.common.model.ProjectInfo;
import com.jin.expertsystem.expertsystem.business.common.model.ResultInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/30 12:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendSmsParam {
    private List<ExpertInfo> expertInfo;
    private ProjectInfo projectInfo;
    private String type;
    private List<String> sendId;
    private String sendText;
}
