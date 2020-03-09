package com.jin.expertsystem.expertsystem.business.login.model;

import com.jin.expertsystem.expertsystem.business.common.model.Menus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuInfo {

    /**
     * 活动主要信息
     */
    private Menus menus;

    /**
     * 活动票量信息
     */
    private List<Menus> menusList = new ArrayList<>();
}
