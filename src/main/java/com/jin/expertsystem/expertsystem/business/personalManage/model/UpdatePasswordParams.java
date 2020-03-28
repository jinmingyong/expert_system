package com.jin.expertsystem.expertsystem.business.personalManage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jinmingyong
 * @date 2020/3/24 13:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordParams {
    private String oldPassword;
    private String newPassword;
}
