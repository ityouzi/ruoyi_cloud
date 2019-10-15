package com.ityouzi.system.feign;

import com.ityouzi.constant.ServiceNameConstants;
import com.ityouzi.core.domain.R;
import com.ityouzi.system.domain.SysUser;
import com.ityouzi.system.feign.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @date: 2019/10/15-11:51
 * @author: lz
 * @description: 用户 Feign服务层
 */

@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE,fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {

    //通过用户名查找用户
    @GetMapping("user/find/{username}")
    public SysUser selectSysUserByUsername(@PathVariable("username") String username);

    //更新用户登录记录
    @PostMapping("user/update/login")
    public R updateUserLoginRecord(@RequestBody SysUser user);
}
