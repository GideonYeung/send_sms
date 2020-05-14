package com.gideon.sms.notify;

/**
 * 发送短信的返回结果
 *
 * @author GideonYeung
 */
public class SmsResult {
    private boolean successful;
    private Object result;

    /**
     * 短信是否发送成功
     */
    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
