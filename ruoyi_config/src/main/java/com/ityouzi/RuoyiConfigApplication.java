package com.ityouzi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@Slf4j
public class RuoyiConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(RuoyiConfigApplication.class,args);
        log.info("===启动配置中心");
    }
    
}
