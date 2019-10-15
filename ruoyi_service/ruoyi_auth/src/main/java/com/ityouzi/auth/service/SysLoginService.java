package com.ityouzi.auth.service;

import com.ityouzi.constant.Constants;
import com.ityouzi.constant.UserConstants;
import com.ityouzi.enums.UserStatus;
import com.ityouzi.exception.user.UserBlockedException;
import com.ityouzi.exception.user.UserDeleteException;
import com.ityouzi.exception.user.UserNotExistsException;
import com.ityouzi.exception.user.UserPasswordNotMatchException;
import com.ityouzi.system.domain.SysUser;
import com.ityouzi.system.feign.RemoteUserService;
import com.ityouzi.system.util.PasswordUtil;
import com.ityouzi.utils.DateUtils;
import com.ityouzi.utils.IpUtils;
import com.ityouzi.utils.MessageUtils;
import com.ityouzi.utils.ServletUtils;
import com.tiyouzi.common.log.publish.PublishFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录服务
 */
@Component
public class SysLoginService {

    @Autowired
    private RemoteUserService userService;

    /**
     * 登录login
     */
    public SysUser login(String username, String password){

        //用户名或密码为空错误
        if (StringUtils.isAnyBlank(username,password)){
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null"));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内(5-20) 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH){
            PublishFactory.recordLogininfor(username,Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内(2-20) 错误
        if (username.length()<UserConstants.USERNAME_MIN_LENGTH
                || username.length()>UserConstants.USERNAME_MAX_LENGTH){
            PublishFactory.recordLogininfor(username,Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.not.match"));
        }
        // 查询用户信息
        SysUser user = userService.selectSysUserByUsername(username);
        if (user == null){
            PublishFactory.recordLogininfor(username,Constants.LOGIN_FAIL,
                    MessageUtils.message("user.not.exists"));
            throw new UserNotExistsException();
        }
        //判断用户状态(OK:正常,DISABLE:停用,DELETE:删除)
        if (UserStatus.DELETE.getCode().equals(user.getDelFlag())){
            PublishFactory.recordLogininfor(username,Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.delete"));
            throw new UserDeleteException();
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus())){
            PublishFactory.recordLogininfor(username,Constants.LOGIN_FAIL,
                    MessageUtils.message("user.blocked",user.getRemark()));
            throw new UserBlockedException();
        }
        if (!PasswordUtil.matches(user,password)){
            throw new UserPasswordNotMatchException();
        }
        PublishFactory.recordLogininfor(username,Constants.LOGIN_SUCCESS,MessageUtils.message("user.login.success"));
        recordLoginInfo(user);
        return user;

    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user){
        user.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserLoginRecord(user);
    }

    /**
     * 注销
     */
    public void logout(String loginName){
        PublishFactory.recordLogininfor(loginName,Constants.LOGOUT,
                MessageUtils.message("user.logout.success"));
    }

}
