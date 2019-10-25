package com.ityouzi.service;


import com.ityouzi.system.domain.SysUser;

import java.util.List;

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

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUserList(SysUser user);

    /**
     * 校验用户名称是否唯一
     */
    String checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     */
    String checkPhoneUnique(SysUser sysUser);

    /**
     * 校验email是否唯一
     */
    String checkEmailUnique(SysUser sysUser);

    /**
     * 保存用户信息
     */
    int insertUser(SysUser sysUser);

    /**
     * 修改保存用户信息
     */
    int updateUser(SysUser sysUser);

    /**
     * 修改用户详细信息
     */
    int updateUserInfo(SysUser user);
}
