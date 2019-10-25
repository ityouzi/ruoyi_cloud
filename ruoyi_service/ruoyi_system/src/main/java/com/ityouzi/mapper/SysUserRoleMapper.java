package com.ityouzi.mapper;

import com.ityouzi.system.domain.SysUserRole;

import java.util.List;

/**
 * 用户表 数据层
 */
public interface SysUserRoleMapper {

    /**
     * 批量新增用户角色信息
     */
    int batchUserRole(List<SysUserRole> list);

    /**
     * 通过用户ID删除用户和岗位关联
     */
    int deleteUserRoleByUserId(Long userId);
}
