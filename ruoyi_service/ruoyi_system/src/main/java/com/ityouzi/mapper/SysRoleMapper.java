package com.ityouzi.mapper;

import com.ityouzi.system.domain.SysRole;
import com.ityouzi.system.domain.SysRoleMenu;

import java.util.List;

public interface SysRoleMapper {

    /**
     * 通过角色ID查询角色
     */
    SysRole selectRoleById(Long roleId);

    /**
     * 根据条件分页查询角色数据
     */
    List<SysRole> selectRoleList(SysRole sysRole);

    /**
     * 新增角色信息
     */
    int insertRole(SysRole role);

    /**
     * 批量新增角色菜单信息
     */
    int batchRoleMenu(List<SysRoleMenu> list);

    /**
     * 修改角色信息
     */
    int updateRole(SysRole role);

    /**
     * 批量角色用户信息
     */
    int deleteRoleByIds(Long[] roleIds);
}
