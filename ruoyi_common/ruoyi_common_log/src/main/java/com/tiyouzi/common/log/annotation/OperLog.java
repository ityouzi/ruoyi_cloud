package com.tiyouzi.common.log.annotation;

import com.tiyouzi.common.log.enums.BusinessType;
import com.tiyouzi.common.log.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @date: 2019/10/27-19:38
 * @author: lz
 * 接口描述: 自定义操作日志记录注解
 */

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {


    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}
