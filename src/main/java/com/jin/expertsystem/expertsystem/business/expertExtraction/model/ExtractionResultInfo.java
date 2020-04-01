package com.jin.expertsystem.expertsystem.business.expertExtraction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/4/1 15:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"handler"})
public class ExtractionResultInfo {
    private String resultId;
    private String proId;
    private String type;
    private String proName;
    private Date createTime;
    private Integer extractNum;
    private Integer moreExtNum;
    private List<ExtractionResultDetailInfo> children;
}
