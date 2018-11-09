package com.flower.mine.exception;

public class SmsSendError extends BaseRuntimeException {
    public SmsSendError() {
        this.setStatus(500);
        this.setCode("sms.send.error");
    }
}
