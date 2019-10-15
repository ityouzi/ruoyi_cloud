package com.ityouzi.auth.service;

import cn.hutool.core.util.IdUtil;
import com.ityouzi.constant.Constants;
import com.ityouzi.redis.annotation.RedisEvict;
import com.ityouzi.redis.util.RedisUtils;
import com.ityouzi.system.domain.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * token
 */
@Service("accessTokenService")
public class AccessTokenService {

    @Autowired
    private RedisUtils redis;      //reids工具


    /**
     * 12小时后过期
     */
    private final static long expire = 12*60*60;
    private final static String access_token = Constants.ACCESS_TOKEN;
    private final static String access_userId = Constants.ACCESS_USERID;

    //通过token获取user对象
    public SysUser queryByToken(String token){
        return redis.get(access_token+token,SysUser.class);
    }

    @RedisEvict(key = "user_perms", fieldkey = "#sysUser.userId")
    public Map<String, Object> createToken(SysUser sysUser){

        //生成token
        String token = IdUtil.fastSimpleUUID();
        //保存或更新用户token
        Map<String, Object> map = new HashMap<>();
        map.put("userId", sysUser.getUserId());
        map.put("token", token);
        map.put("expire",expire);   //有效期

        redis.set(access_token+token,sysUser,expire);
        redis.set(access_userId+sysUser.getUserId(),token,expire);
        return map;
    }

    public void expireToken(long userId){
        //获取token
        String token = redis.get(access_userId + userId);
        if (StringUtils.isNotBlank(token)){
            redis.delete(access_userId+userId);
            redis.delete(access_token+token);
        }
    }



}
