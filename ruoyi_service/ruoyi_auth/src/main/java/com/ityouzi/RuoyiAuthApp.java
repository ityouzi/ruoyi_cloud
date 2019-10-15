package com.ityouzi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 授权中心
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@Slf4j
public class RuoyiAuthApp {
    public static void main(String[] args) {
        SpringApplication.run(RuoyiAuthApp.class,args);
        log.info("===授权中心");
    }

}
