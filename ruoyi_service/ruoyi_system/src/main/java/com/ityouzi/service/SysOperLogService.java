package com.ityouzi.service;

import com.ityouzi.system.domain.SysOperLog;

import java.util.List;

/**
 * 2019/10/27-19:26
 * 操作日志 服务层
 */
public interface SysOperLogService {

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
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteOperLogByIds(String ids);
    /**
     * 清空操作日志
     */
    void cleanOperLog();
}
