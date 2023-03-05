package com.zh.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
* @Description 启动类
* @Author yuzhenhai
* @Date 2023/2/24 0024 13:40
**/
@SpringBootApplication
@MapperScan("com.zh.store.mapper")
@ComponentScan("com.zh.store.service")
@ComponentScan(basePackages = {"com.zh.store.config"})
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
