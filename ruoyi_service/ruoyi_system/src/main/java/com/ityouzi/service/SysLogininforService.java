package com.ityouzi.service;


import com.ityouzi.system.domain.SysLogininfo;

import java.util.List;

/**
 * 2019/10/28-10:05
 * 系统访问日志情况信息 服务层
 */
public interface SysLogininforService {

    /**
     * 查询系统登录日志集合
     *
     * @param logininfo 访问日志对象
     * @return 登录记录集合
     */
    List<SysLogininfo> selectLogininforList(SysLogininfo logininfo);

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(SysLogininfo logininfor);

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    int deleteLogininforByIds(String ids);

    /**
     * 清空系统登录日志
     */
    void cleanLogininfor();
}
