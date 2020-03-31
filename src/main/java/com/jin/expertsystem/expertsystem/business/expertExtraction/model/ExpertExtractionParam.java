package com.jin.expertsystem.expertsystem.business.expertExtraction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/30 13:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertExtractionParam {
    private List<String> cityList;
    private List<Integer> jobGradeList;
    private List<Integer> industryList;
    private List<Integer> companyList;
    private List<Integer> majorList;
    private String name;
    private Integer pageNum;
    private Integer pageSize;
    private Integer num;
}
