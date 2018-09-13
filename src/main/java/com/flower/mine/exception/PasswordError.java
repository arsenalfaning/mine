package com.flower.mine.exception;

public class PasswordError extends BaseRuntimeException {
    public PasswordError() {
        setCode("password.error");
        setStatus(400);
    }
}
