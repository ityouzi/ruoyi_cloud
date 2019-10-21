package com.ityouzi.service.impl;


import com.ityouzi.mapper.SysUserMapper;
import com.ityouzi.service.ISysUserService;
import com.ityouzi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2019/10/17-9:16
 * @author: lz
 * @description: 用户  业务层处理
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

   @Autowired
   private SysUserMapper sysUserMapper;



    /**
     * 通过用户ID查询用户
     * @param userId 用户ID
     */
    @Override
    public SysUser selectUserById(String userId) {
        return sysUserMapper.selectUserById(userId);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByLoginName(String userName) {
        return sysUserMapper.selectUserByLoginName(userName);
    }
}
