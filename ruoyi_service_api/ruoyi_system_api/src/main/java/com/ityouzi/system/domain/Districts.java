package com.ityouzi.system.domain;

import com.ityouzi.core.domain.BaseEntity;
import lombok.Data;
/**
 * 2019/10/28-11:10
 * 地区表 districts
 */
@Data
public class Districts extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Integer           id;

    /** 上级编号 */
    private Integer           pid;

    /** 层级 */
    private Integer           deep;

    /** 名称 */
    private String            name;

    /** 上级名称 */
    private String            pname;

    /** 拼音 */
    private String            pinyin;

    /** 拼音缩写 */
    private String            pinyinShor;

    /** 扩展名 */
    private String            extName;

    /** 操作人 */
    private String            operator;


}
