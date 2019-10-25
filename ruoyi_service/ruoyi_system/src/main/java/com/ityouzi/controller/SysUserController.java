package com.ityouzi.controller;

import com.ityouzi.annotation.LoginUser;
import com.ityouzi.auth.annotation.HasPermissions;
import com.ityouzi.constant.UserConstants;
import com.ityouzi.core.controller.BaseController;
import com.ityouzi.core.domain.ResultMsg;
import com.ityouzi.service.ISysMenuService;
import com.ityouzi.service.ISysUserService;
import com.ityouzi.system.domain.SysUser;
import com.ityouzi.system.util.PasswordUtil;
import com.ityouzi.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @date: 2019/10/17-9:14
 * @author: lz
 * @description: 用户 提供者
 */
@RestController
@RequestMapping("user")
public class SysUserController extends BaseController {

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

    /**
     * 2019/10/25-15:10
     * 查询用户列表
     */
    @GetMapping("list")
    public ResultMsg list(SysUser sysUser){
        startPage();        //开启分页
        List<SysUser> sysUserList = sysUserService.selectUserList(sysUser);
        return result(sysUserList);
    }

    /**
     * 2019/10/25-15:26
     * 新增保存用户
     */
    @HasPermissions("system:user:add")
    @PostMapping("save")
    public ResultMsg save(@RequestBody SysUser sysUser){

        //用户名唯一性验证
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(sysUserService.checkLoginNameUnique(sysUser.getLoginName()))){
            return ResultMsg.error("新增用户'" + sysUser.getLoginName() + "'失败，登录账号已存在");
        }
        //手机号唯一性
        else if(UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(sysUser))){
            return ResultMsg.error("新增用户'" + sysUser.getLoginName() + "'失败，手机号码已存在");
        }
        //邮箱唯一性
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(sysUser))){
            return ResultMsg.error("新增用户'" + sysUser.getLoginName() + "'失败，邮箱账号已存在");
        }
        sysUser.setSalt(RandomUtil.randomStr(6));   //密码盐
        sysUser.setPassword(
                PasswordUtil.encryptPassword(
                        sysUser.getLoginName(),     //登录名
                        sysUser.getPassword(),      //密码
                        sysUser.getSalt())          //盐
        );
        sysUser.setCreateBy(getLoginName());        //创建人
        return toAjax(sysUserService.insertUser(sysUser));
    }


    /**
     * 2019/10/25-16:24
     * 修改保存用户
     */
    @HasPermissions("system:user:edit")
    @PostMapping("update")
    public ResultMsg editSave(@RequestBody SysUser sysUser){
        if (sysUser.getUserId() != null && SysUser.isAdmin(sysUser.getUserId())){
            return ResultMsg.error("不允许修改超级管理员用户");
        }
        else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(sysUser))){
            return ResultMsg.error("修改用户'" + sysUser.getLoginName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(sysUser))){
            return ResultMsg.error("修改用户'" + sysUser.getLoginName() + "'失败，邮箱账号已存在");
        }
        return toAjax(sysUserService.updateUser(sysUser));
    }

    /**
     * 2019/10/25-16:58
     * 修改用户信息
     */
    @HasPermissions("system:user:edit")
    @PostMapping("update/info")
    public ResultMsg updateInfo(@RequestBody SysUser sysUser){
        return toAjax(sysUserService.updateUserInfo(sysUser));
    }

    /**
     * 2019/10/25-17:01
     * 记录登录信息
     */
    @PostMapping("update/login")
    public ResultMsg updateLoginRecord(@RequestBody SysUser user){
        return toAjax(sysUserService.updateUser(user));
    }





}
