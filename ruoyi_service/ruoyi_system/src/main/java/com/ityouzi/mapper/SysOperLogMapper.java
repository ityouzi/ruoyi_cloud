package com.ityouzi.mapper;

import com.ityouzi.system.domain.SysOperLog;

import java.util.List;

/**
 * 2019/10/27-19:27
 * 操作日志 数据层
 */
public interface SysOperLogMapper {
    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    SysOperLog selectOperLogById(Long operId);

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperlog(SysOperLog operLog);

    /**
     * 批量删除系统操作日志
     */
    int deleteOperLogByIds(String[] ids);


    void cleanOperLog();
}
