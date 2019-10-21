package com.ityouzi.controller;

import com.ityouzi.annotation.LoginUser;
import com.ityouzi.service.ISysMenuService;
import com.ityouzi.service.ISysUserService;
import com.ityouzi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @date: 2019/10/17-9:14
 * @author: lz
 * @description: 用户 提供者
 */
@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired  //用户
    private ISysUserService sysUserService;
    @Autowired  //菜单
    private ISysMenuService sysMenuService;


    /**
     * 用户查询
     */
    @GetMapping("get/{userId}")
    public SysUser get(@PathVariable("userId") String userId){
        return sysUserService.selectUserById(userId);
    }

    /**
     * 查询用户权限
     */
    @GetMapping("info")
    public SysUser info(@LoginUser SysUser sysUser){
        //根据用户ID查询权限
        Set<String> premissions = sysMenuService.selectPermsByUserId(sysUser.getUserId());
        sysUser.setButtons(premissions);
        return sysUser;
    }

    /**
     * 查询用户
     */
    @GetMapping("find/{username}")
    public SysUser findByUsername(@PathVariable("username") String username){
        return sysUserService.selectUserByLoginName(username);
    }



}
