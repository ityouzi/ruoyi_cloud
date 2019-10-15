package com.ityouzi.system.util;

import com.ityouzi.system.domain.SysUser;
import com.ityouzi.utils.security.Md5Utils;

public class PasswordUtil {

    public static boolean matches(SysUser user,String newPassword){
        return user.getPassword().equals(encryptPassword(user.getLoginName(),newPassword,user.getSalt()));
    }

    /**
     * 加密
     * @param username 用户名
     * @param password 密码
     * @param salt 盐
     * @return
     */
    public static String encryptPassword(String username,String password,String salt){
        String hash = Md5Utils.hash(username + password + salt);
        return hash;
    }


}
