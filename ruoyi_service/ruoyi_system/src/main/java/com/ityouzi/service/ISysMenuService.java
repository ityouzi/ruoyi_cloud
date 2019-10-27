package com.ityouzi.service;

import com.ityouzi.system.domain.SysMenu;
import com.ityouzi.system.domain.SysUser;

import java.util.List;
import java.util.Set;

/**
 * 菜单 业务层
 */
public interface ISysMenuService {


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectPermsByUserId(Long userId);

    /**
     * 根据菜单ID查询信息
     */
    SysMenu selectMenuById(Long menuId);

    /**
     * 根据用户ID查询菜单
     */
    List<SysMenu> selectMenusByUser(SysUser sysUser);

    /**
     * 根据角色ID查询菜单ID
     */
    List<SysMenu> selectMenuIdsByRoleId(Long roleId);

    /**
     * 查询系统菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu sysMenu);

    /**
     * 新增保存菜单信息
     */
    int insertMenu(SysMenu menu);

    /**
     * 修改保存菜单信息
     */
    int updateMenu(SysMenu menu);

    /**
     * 删除菜单管理信息
     */
    int deleteMenuById(Long menuId);
}
