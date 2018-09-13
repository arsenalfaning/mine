package com.flower.mine.exception;

public class ResetPasswordError extends BaseRuntimeException {
    public ResetPasswordError() {
        setCode("password.reset.error");
        setStatus(400);
    }
}
