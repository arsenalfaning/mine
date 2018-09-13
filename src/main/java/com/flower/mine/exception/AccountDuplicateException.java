package com.flower.mine.exception;

public class AccountDuplicateException extends BaseRuntimeException {
    public AccountDuplicateException() {
        this.status = 400;
        this.setCode("account.create.duplicate");
    }
}
