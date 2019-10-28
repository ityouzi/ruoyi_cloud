package com.ityouzi.config;


import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @date: 2019/10/28-16:50
 * @author: lz
 * 描述: 路由限流配置
 */

@Configuration
public class RateLimiterConfiguration {

    @Bean(value = "remoteAddrKeyResolver")
    public KeyResolver remoteAddrKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest()
                .getRemoteAddress()
                .getAddress()
                .getHostAddress());


    }
}
