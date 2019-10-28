package com.ityouzi.exception;

/**
 * 2019/10/28-14:22
 * 验证码校验
 */
public class ValidateCodeException extends Exception{
    private static final long serialVersionUID = 3887472968823615091L;

    public ValidateCodeException(){

    }

    public ValidateCodeException(String msg){
        super(msg);
    }


}
