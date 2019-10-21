package com.ityouzi.mapper;

import com.ityouzi.system.domain.SysUser;

/**
 * 用户表 数据层
 */
public interface SysUserMapper {

    /**
     * 通过用户ID查询用户
     * @param userId 用户ID
     * @return  用户对象信息
     */
    SysUser selectUserById(String userId);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUser selectUserByLoginName(String userName);
}
