package com.ityouzi.utils;

import java.io.File;


/**
 * @date: 2019/10/27-19:57
 * @author: lz
 * 描述: 高频方法集合类
 */
public class ToolUtil {

    /**
     * 获取临时目录
     * @author zmr
     */
    public static String getTempPath()
    {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 获取当前项目工作目录
     * @return
     * @author zmr
     */
    public static String getUserDir()
    {
        return System.getProperty("user.dir");
    }

    /**
     * 获取临时下载目录
     * @return
     * @author zmr
     */
    public static String getDownloadPath()
    {
        return getTempPath() + File.separator + "download" + File.separator;
    }
}
