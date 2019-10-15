package com.ityouzi.enums;

import lombok.Getter;

/**
 * 用户状态
 */
@Getter
public enum  UserStatus {

    OK("0","正常"),
    DISABLE("1","停用"),
    DELETE("2","删除");


    private final String code;
    private final String info;
    //构造函数
    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }}
