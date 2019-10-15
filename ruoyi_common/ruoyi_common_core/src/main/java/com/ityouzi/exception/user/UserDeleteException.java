package com.ityouzi.exception.user;

/**
 * 用户帐号已被删除
 */
public class UserDeleteException extends UserException{

    private static final long serialVersionUID = 1L;

    public UserDeleteException() {
        super("user.password.delete",null);
    }
}
