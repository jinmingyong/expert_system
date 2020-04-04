package com.jin.expertsystem.expertsystem.business.personalManage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jinmingyong
 * @date 2020/4/2 16:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyMessageParam {
    private String mesId;
    private String resId;
    private String expId;
    private String flagSts;
}
