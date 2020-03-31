package com.jin.expertsystem.expertsystem.business.sysmanage.service;

import com.jin.expertsystem.expertsystem.business.sysmanage.model.SendSmsParam;

/**
 * @author jinmingyong
 * @date 2020/3/30 12:47
 */
public interface SendSmsService {
    Integer sendSms(SendSmsParam sendSmsParam);
}
