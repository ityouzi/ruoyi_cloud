package com.ityouzi.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ityouzi.core.domain.ResultMsg;
import com.ityouzi.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static com.ityouzi.constant.Constants.*;

/**
 * @date: 2019/10/28-15:07
 * @author: lz
 * 描述: 网关鉴权
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    // 排除过滤的 uri 地址(白名单)
    // swagger排除自行添加
    private static final String[] whiteList = {
            "/auth/login",
            "/user/register",
            "/system/v2/api-docs"
    };


    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> ops;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        log.info("请求路径url:{}", url);
        //跳过不需要验证的路径
        if (Arrays.asList(whiteList).contains(url)){
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getHeaders().getFirst(TOKEN);
        //token为空
        if (StringUtils.isBlank(token)){
            return setUnauthorizedResponse(exchange, "token can't null or empty string");
        }
        String userStr = ops.get(ACCESS_TOKEN + token);
        JSONObject json = JSONObject.parseObject(userStr);
        String userId = json.getString("userId");
        //查询token信息
        if (StringUtils.isBlank(userId)){
            return setUnauthorizedResponse(exchange, "token verify error");
        }
        // 设置userId到request里，后续根据userId，获取用户信息
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header(CURRENT_ID, userId)
                .header(CURRENT_USERNAME,json.getString("loginName"))
                .build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }


    /**
     * 没有权限
     */
    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String msg){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] res = null;

        try {
            res = JSON.toJSONString(ResultMsg.error(401, msg)).getBytes(UTF8);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        DataBuffer buffer = response.bufferFactory().wrap(res);
        return response.writeWith(Flux.just(buffer));

    }


    @Override
    public int getOrder() {
        return -200;
    }
}
