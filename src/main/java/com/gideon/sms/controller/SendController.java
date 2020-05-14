package com.gideon.sms.controller;

import com.gideon.sms.notify.NotifyService;
import com.gideon.sms.notify.NotifyType;
import com.gideon.sms.notify.SmsResult;
import com.gideon.sms.utils.RegexUtil;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author GideonYeung
 * @date 2020/5/14 15:24
 */
@RestController
@RequestMapping("/sms")
public class SendController {

    @Autowired
    NotifyService notifyService;

    @ResponseBody
    @GetMapping("/sendSms")
    public SmsResult sendSms(@NotNull String phone) {
        if (!RegexUtil.isMobileExact(phone)) {
            SmsResult smsResult = new SmsResult();
            smsResult.setSuccessful(false);
            smsResult.setResult("手机号码不正确!");
            return smsResult;
        }
        return notifyService.notifySmsTemplateSync(phone, NotifyType.CAPTCHA, new String[]{getMsgCode()});
    }

    /**
     * 功能描述: 生成六位随机验证码
     */
    private static String getMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }
}
