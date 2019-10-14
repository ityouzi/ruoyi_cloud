package com.ityouzi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class RuoyiEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(RuoyiEurekaApplication.class,args);
        log.info("===启动注册中心");
    }

}
