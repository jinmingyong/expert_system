package com.jin.expertsystem.expertsystem.business.sysmanage.service.impl;

import com.jin.expertsystem.expertsystem.business.sysmanage.model.SendSmsParam;
import com.jin.expertsystem.expertsystem.business.sysmanage.service.SendSmsService;
import org.springframework.stereotype.Service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

/**
 * @author jinmingyong
 * @date 2020/3/30 12:48
 */
@Service
public class SendSmsServiceImpl implements SendSmsService {

    public Boolean sendEmail(String email,String msg){
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1060854946@qq.com","albudmqrijwlbebf");
            }
        });
        // 获取邮件对象
        Message message = new MimeMessage(session);
        try {
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("1060854946@qq.com"));
        // 设置收件人邮箱地址
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(email));//当邮件有多个收件人时，用逗号隔开

        message.setSubject("专家库管理系统通知");
        // 设置邮件内容
        message.setText(msg);
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("1060854946@qq.com", "albudmqrijwlbebf");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
