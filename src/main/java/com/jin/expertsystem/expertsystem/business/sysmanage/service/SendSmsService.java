package com.jin.expertsystem.expertsystem.business.sysmanage.service;

import com.jin.expertsystem.expertsystem.business.sysmanage.model.SendSmsParam;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

/**
 * @author jinmingyong
 * @date 2020/3/30 12:47
 */
public interface SendSmsService {
    List<String> sendEmail(List<String> emailList,String msg) throws MessagingException;
}
