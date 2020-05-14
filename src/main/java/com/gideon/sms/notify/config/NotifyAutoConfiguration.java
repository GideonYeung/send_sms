package com.gideon.sms.notify.config;

import com.gideon.sms.notify.AliyunSmsSender;
import com.gideon.sms.notify.NotifyService;
import com.gideon.sms.notify.TencentSmsSender;
import com.github.qcloudsms.SmsSingleSender;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author GideonYeung
 */
@Configuration
@EnableConfigurationProperties(NotifyProperties.class)
public class NotifyAutoConfiguration {

    private final NotifyProperties properties;

    private static final String TENCENT = "tencent";
    private static final String ALIYUN = "aliyun";

    public NotifyAutoConfiguration(NotifyProperties properties) {
        this.properties = properties;
    }

    @Bean
    public NotifyService notifyService() {
        NotifyService notifyService = new NotifyService();

        NotifyProperties.Sms smsConfig = properties.getSms();
        if (smsConfig.isEnable()) {
            if(TENCENT.equals(smsConfig.getActive())) {
                notifyService.setSmsSender(tencentSmsSender());
            }
            else if(ALIYUN.equals(smsConfig.getActive())) {
                notifyService.setSmsSender(aliyunSmsSender());
            }

            notifyService.setSmsTemplate(smsConfig.getTemplate());
        }

        return notifyService;
    }

    public TencentSmsSender tencentSmsSender() {
        NotifyProperties.Sms smsConfig = properties.getSms();
        TencentSmsSender smsSender = new TencentSmsSender();
        NotifyProperties.Sms.Tencent tencent = smsConfig.getTencent();
        smsSender.setSender(new SmsSingleSender(tencent.getAppid(), tencent.getAppkey()));
        smsSender.setSign(smsConfig.getSign());
        return smsSender;
    }

    public AliyunSmsSender aliyunSmsSender() {
        NotifyProperties.Sms smsConfig = properties.getSms();
        AliyunSmsSender smsSender = new AliyunSmsSender();
        NotifyProperties.Sms.Aliyun aliyun = smsConfig.getAliyun();
        smsSender.setSign(smsConfig.getSign());
        smsSender.setRegionId(aliyun.getRegionId());
        smsSender.setAccessKeyId(aliyun.getAccessKeyId());
        smsSender.setAccessKeySecret(aliyun.getAccessKeySecret());
        return smsSender;
    }
}
