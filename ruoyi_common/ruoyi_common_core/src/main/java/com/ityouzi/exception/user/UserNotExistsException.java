package com.ityouzi.exception.user;

/**
 * 用户不存在异常类
 */
public class UserNotExistsException extends UserException{
    private static final long serialVersionUID = 1L;

    public UserNotExistsException() {
        //语言库中对应"用户不存在"
        super("user.not.exists", null);
    }
}
