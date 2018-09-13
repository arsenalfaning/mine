package com.flower.mine.exception;

public class SmsError extends BaseRuntimeException {
    public SmsError() {
        this.setStatus(400);
        this.setCode("sms.error");
    }
}
