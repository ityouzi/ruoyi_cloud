package com.ityouzi.generate.util;

import com.ityouzi.generate.config.GenConfig;
import com.ityouzi.generate.domain.GenTable;
import com.ityouzi.utils.StringUtils;

/**
 * 代码生成器 工具类
 */
public class GenUtils {

    /**
     * 初始化
     */
    public static void initTable(GenTable genTable, String operName){
        genTable.setClassName(convertClassName(genTable.getTableName()));



    }




    /**
     * 表名转换成Java 类名
     */
    public static String convertClassName(String tableName){
        boolean autoRemovePre = GenConfig.getAutoRemovePre();
        String tablePrefix = GenConfig.getTablePrefix();    //表前缀
        if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix)){
            tableName = tableName.replaceFirst(tablePrefix, "");
        }
        return StringUtils.convertToCamelCase(tableName);
    }

}
