package com.ityouzi.service;


import com.ityouzi.system.domain.SysUser;

/**
 * 用户 业务层
 */
public interface ISysUserService {

    /**
     * 通过用户ID查询用户
     * @param userId
     */
    public SysUser selectUserById(String userId);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByLoginName(String userName);
}
