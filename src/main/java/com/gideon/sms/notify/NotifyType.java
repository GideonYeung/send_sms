package com.gideon.sms.notify;

/**
 * @author GideonYeung
 */

public enum NotifyType {
    /**
     * 支付成功
     */
    PAY_SUCCEED("paySucceed"),
    /**
     * 下单
     */
    SHIP("ship"),
    /**
     * 退款
     */
    REFUND("refund"),
    /**
     * 验证码
     */
    CAPTCHA("captcha");

    private String type;

    NotifyType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
