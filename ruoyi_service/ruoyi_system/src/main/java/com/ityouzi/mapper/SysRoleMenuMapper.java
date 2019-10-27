package com.ityouzi.mapper;

/**
 * 角色与菜单关联表 数据层
 */
public interface SysRoleMenuMapper {

    /**
     * 通过角色ID删除角色和菜单关联
     */
    int deleteRoleMenuByRoleId(Long roleId);
}
