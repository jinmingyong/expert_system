package com.jin.expertsystem.expertsystem.business.sysmanage.controller;

import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.SendSmsParam;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jinmingyong
 * @date 2020/3/30 12:46
 */
@Api(tags = "发送短信")
@RestController
@RequestMapping("/sendSmsController/")
public class SendSms {
    @PostMapping(value = "/sendForgetSms")
    public Result sendSms(@RequestBody SendSmsParam sendSmsParam) {
        return null;
    }

}
