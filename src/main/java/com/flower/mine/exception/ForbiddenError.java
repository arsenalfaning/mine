package com.flower.mine.exception;

public class ForbiddenError extends BaseRuntimeException {
    public ForbiddenError() {
        setCode("error.forbidden");
        setStatus(403);
    }
}
