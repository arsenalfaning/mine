package com.flower.mine.exception;

public class LoginError extends BaseRuntimeException {
    public LoginError() {
        setCode("login.error");
        setStatus(400);
    }
}
