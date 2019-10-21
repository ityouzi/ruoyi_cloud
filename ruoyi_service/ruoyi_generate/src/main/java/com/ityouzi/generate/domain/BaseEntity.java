package com.ityouzi.generate.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Entity 基类(共有字段)
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 搜索值 */
    private String              searchValue;

    /** 创建者 */
    private String              createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String              updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date                updateTime;

    /** 备注 */
    private String              remark;
    /** 开始时间 */
    private String              beginTime;
    /** 结束时间 */
    private String              endTime;

    /** 请求参数 */
    private Map<String, Object> params;


}
