package com.flower.mine.util;

import com.flower.mine.exception.ForbiddenError;
import com.flower.mine.session.SessionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class RoleAspect {

    @Pointcut("@annotation(com.flower.mine.util.Role)")
    private void cut() {

    }

    @Around(value = "cut()&&@annotation(role)", argNames = "joinPoint, role")
    public Object check(ProceedingJoinPoint joinPoint, Role role) throws Throwable {
        if ( SessionUtil.current().getAdmin() ) {
            return joinPoint.proceed();
        }
        throw new ForbiddenError();
    }
}
