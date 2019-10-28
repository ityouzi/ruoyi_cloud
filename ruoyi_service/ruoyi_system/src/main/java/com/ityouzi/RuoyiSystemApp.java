package com.ityouzi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 系统服务
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.ityouzi.*.mapper")
@Slf4j
public class RuoyiSystemApp {
    public static void main(String[] args) {
        SpringApplication.run(RuoyiSystemApp.class,args);
        log.info("=====>>>启动系统服务");
    }




}
