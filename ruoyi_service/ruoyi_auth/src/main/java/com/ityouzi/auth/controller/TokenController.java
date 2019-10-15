package com.ityouzi.auth.controller;

import com.ityouzi.auth.form.LoginForm;
import com.ityouzi.auth.service.AccessTokenService;
import com.ityouzi.auth.service.SysLoginService;
import com.ityouzi.core.domain.R;
import com.ityouzi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TokenController {
    @Autowired
    private AccessTokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public R login(@RequestBody LoginForm from){
        //用户登录
        SysUser user = sysLoginService.login(from.getUsername(), from.getPassword());
        //获取登录token
        return R.ok(tokenService.createToken(user));
    }

    /**
     * 注销
     */
    @PostMapping("/logout")
    public R logout(HttpServletRequest request){
        String token = request.getHeader("token");
        SysUser user = tokenService.queryByToken(token);        //通过token获取User对象
        if (null != user){
            sysLoginService.logout(user.getLoginName());
            tokenService.expireToken(user.getUserId());         //清除token
        }
        return R.ok();
    }

}
