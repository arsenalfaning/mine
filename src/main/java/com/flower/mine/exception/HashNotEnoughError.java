package com.flower.mine.exception;

public class HashNotEnoughError extends BaseRuntimeException {
    public HashNotEnoughError() {
        setCode("hash.not.enough");
        setStatus(400);
    }
}
