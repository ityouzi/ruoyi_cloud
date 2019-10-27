package com.ityouzi.service.impl;


import com.ityouzi.annotation.DataScope;
import com.ityouzi.constant.UserConstants;
import com.ityouzi.core.text.Convert;
import com.ityouzi.exception.BusinessException;
import com.ityouzi.mapper.SysUserMapper;
import com.ityouzi.mapper.SysUserPostMapper;
import com.ityouzi.mapper.SysUserRoleMapper;
import com.ityouzi.service.ISysUserService;
import com.ityouzi.system.domain.SysUser;
import com.ityouzi.system.domain.SysUserPost;
import com.ityouzi.system.domain.SysUserRole;
import com.ityouzi.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2019/10/17-9:16
 * @author: lz
 * @description: 用户  业务层处理
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

   @Autowired
   private SysUserMapper sysUserMapper;

   @Autowired
   private SysUserPostMapper userPostMapper;

   @Autowired
   private SysUserRoleMapper userRoleMapper;



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

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectUserList(SysUser user) {
        return sysUserMapper.selectUserList(user);
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 用户名
     */
    @Override
    public String checkLoginNameUnique(String loginName) {
        int count = sysUserMapper.checkLoginNameUnique(loginName);
        if (count > 0){
            return UserConstants.USER_NAME_NOT_UNIQUE;  //1 不唯一
        }
        return UserConstants.USER_NAME_UNIQUE;  //0 唯一
    }

    /**
     * 校验手机号码是否唯一
     * @param sysUser
     */
    @Override
    public String checkPhoneUnique(SysUser sysUser) {
        Long userId = StringUtils.isNull(sysUser.getUserId()) ? -1l : sysUser.getUserId();
        SysUser info = sysUserMapper.checkPhoneUnique(sysUser.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()){
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     * @param sysUser
     */
    @Override
    public String checkEmailUnique(SysUser sysUser) {
        Long userId = StringUtils.isNull(sysUser.getUserId()) ? -1L : sysUser.getUserId();
        SysUser info = sysUserMapper.checkEmailUnique(sysUser.getEmail());

        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()){
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }

    /**
     * 保存用户信息
     *
     * @param sysUser
     */
    @Override
    @Transactional
    public int insertUser(SysUser sysUser) {
        //新增用户信息
        int rows = sysUserMapper.insertUser(sysUser);
        //新增用户岗位关联
        insertUserPost(sysUser);
        //新增用户与角色管理
        insertUserRole(sysUser);
        return rows;
    }

    /**
     * 修改保存用户信息
     *
     * @param sysUser
     */
    @Override
    @Transactional
    public int updateUser(SysUser sysUser) {
        Long userId = sysUser.getUserId();
        //删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理
        insertUserPost(sysUser);
        // 删除用户与岗位关联
        userPostMapper.deleteUserPostByUserId(userId);
        // 新增用户与岗位管理
        insertUserPost(sysUser);
        return sysUserMapper.updateUser(sysUser);
    }

    /**
     * 修改用户详细信息
     *
     * @param user
     */
    @Override
    public int updateUserInfo(SysUser user) {
        return sysUserMapper.updateUser(user);
    }

    /**
     * 重置密码
     */
    @Override
    public int resetUserPwd(SysUser user) {
        return updateUserInfo(user);
    }
    
    /**
     * 2019/10/27-13:52
     * 用户状态修改
     */
    @Override
    public int changeStatus(SysUser user) {
        if (user.isAdmin(user.getUserId())){
            throw new BusinessException("不允许修改超级管理员用户");
        }
        return sysUserMapper.updateUser(user);
    }

    /**
     * 2019/10/27-14:21
     * 批量删除用户信息
     */
    @Override
    public int deleteUserByIds(String ids) {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId:userIds){
            if (SysUser.isAdmin(userId)){
                throw new BusinessException("不允许删除超级管理员用户");
            }
        }
        return sysUserMapper.deleteUserByIds(userIds);
    }

    /**
     * 新增用户岗位信息
     */
    public void insertUserPost(SysUser sysUser){
        Long[] postIds = sysUser.getPostIds();
        if (StringUtils.isNotNull(postIds)){
            //新增用户与岗位管理
            List<SysUserPost> list = new ArrayList<>();
            for (Long postId:postIds){
                SysUserPost up = new SysUserPost();
                up.setUserId(sysUser.getUserId());
                up.setPostId(postId);
                list.add(up);
            }
            if (list.size() > 0){       //批量新增用户岗位信息
                userPostMapper.batchUserPost(list);
            }
        }
    }

    /**
     * 新增用户角色信息
     */
    public void insertUserRole(SysUser user){
        List<Long> roleIds = user.getRoleIds();
        if (StringUtils.isNotNull(roleIds)){
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<>();
            for (Long roleId:roleIds){
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size()>0){     //批量新增用户角色信息
                userRoleMapper.batchUserRole(list);
            }
        }
    }










}
