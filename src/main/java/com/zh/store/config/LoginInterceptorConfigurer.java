package com.zh.store.config;

import com.zh.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @Description 注册处理器拦截器
 * @Author yuzhenhai
 * @Date 2023/02/23 21:05
 **/

public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //访问的路径    当访问/upload/**路径下，会去Locations的路径文件上找
        registry.addResourceHandler("/upload/**")
                //本地路径
                .addResourceLocations("file:C:/Users/Administrator/AppData/Local/Temp/tomcat-docbase.8080.4965987618714301036/upload/");

    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建一个拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        System.out.println("拦截器------");
        // 白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");
        // 通过注册工具添加拦截器
        //addPathPatterns：用于设置拦截器的过滤路径规则；addPathPatterns("/**")对所有请求都拦截
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns
                (patterns);
    }

}
