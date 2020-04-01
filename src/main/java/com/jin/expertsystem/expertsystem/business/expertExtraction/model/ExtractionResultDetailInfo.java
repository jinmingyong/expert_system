package com.jin.expertsystem.expertsystem.business.expertExtraction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jinmingyong
 * @date 2020/4/1 15:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"handler"})
public class ExtractionResultDetailInfo {
    private String id;
    private String resId;
    private String expId;
    private String flagEmail;
    private String flagSts;
    private String name;
    private String age;
    private String sex;
    private String jobGrade;
    private String industry;
    private String company;
    private String major;
    private String city;
}
