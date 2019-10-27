package com.ityouzi.service;

import com.ityouzi.system.domain.SysRole;

import java.util.List;

public interface SysRoleService {


    /**
     * 查询角色
     */
    SysRole selectRoleById(Long roleId);

    /**
     * 根据条件分页查询角色数据
     */
    List<SysRole> selectRoleList(SysRole sysRole);

    /**
     * 查询所有角色
     */
    List<SysRole> selectRoleAll();

    /**
     * 新增保存角色信息
     */
    int insertRole(SysRole role);

    /**
     * 修改保存角色信息
     */
    int updateRole(SysRole role);

    /**
     * 角色状态修改
     */
    int changeStatus(SysRole role);

    /**
     * 修改数据权限信息
     */
    int authDataScope(SysRole role);

    /**
     * 删除角色
     */
    int deleteRoleByIds(String ids);

    /**
     * 通过角色ID查询角色使用数量
     */
    int countUserRoleByRoleId(Long roleId);

}
