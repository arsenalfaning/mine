package com.flower.mine.exception;

public class NotFoundError extends BaseRuntimeException {
    public NotFoundError() {
        setCode("not.found");
        setStatus(404);
    }
}
