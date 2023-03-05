package com.zh.store.config;


import com.zh.store.filter.LogCostFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * TODO
 *
 * @Description 注册过滤器
 * @Author yuzhenhai
 * @Date 2023/02/24 0:41
 **/
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registration(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //实例化Filter类
        filterRegistrationBean.setFilter(new LogCostFilter());
        //设置匹配模式,这里设置为所有，可以按需求设置
        filterRegistrationBean.addUrlPatterns("/*");
        //设置过滤器名称
        filterRegistrationBean.setName("LogCostFilter");
        //设置执行顺序
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
