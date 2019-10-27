package com.ityouzi.mapper;

import com.ityouzi.system.domain.SysUser;

import java.util.List;

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
    /**
     * 根据条件分页查询用户列表
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 校验用户名称是否唯一
     */
    int checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     */
    SysUser checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     */
    SysUser checkEmailUnique(String email);

    /**
     * 新增用户信息
     */
    int insertUser(SysUser sysUser);

    /**
     * 修改用户信息
     */
    int updateUser(SysUser sysUser);

    /**
     * 批量删除用户信息
     */
    int deleteUserByIds(Long[] userIds);
}
