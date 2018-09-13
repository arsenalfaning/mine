package com.flower.mine.interceptor;

import com.flower.mine.exception.UnLoggedException;
import com.flower.mine.session.SessionUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ( SessionUtil.current() == null ) {
            throw new UnLoggedException();
        }
        return true;
    }
}
