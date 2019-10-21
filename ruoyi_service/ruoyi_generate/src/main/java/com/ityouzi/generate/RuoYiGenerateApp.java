package com.ityouzi.generate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 代码生成
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.tiyouzi.generate.mapper")
public class RuoYiGenerateApp {
    public static void main(String[] args) {
        SpringApplication.run(RuoYiGenerateApp.class,args);
        log.info("=========代码生成启动===========");
    }
}
