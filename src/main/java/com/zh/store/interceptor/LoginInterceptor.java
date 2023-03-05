package com.zh.store.interceptor;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;

/**
 * TODO
 *
 * @Description 登录拦截器
 * @Author yuzhenhai
 * @Date 2023/02/23 20:55
 **/

public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("uid") == null){
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
