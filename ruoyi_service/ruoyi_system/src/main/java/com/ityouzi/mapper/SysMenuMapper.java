package com.ityouzi.mapper;

import com.ityouzi.system.domain.SysMenu;

import java.util.List;

/**
 * 菜单表 数据层
 */
public interface SysMenuMapper {


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<String> selectPermsByUserId(Long userId);


    /**
     * 根据菜单ID查询信息
     */
    SysMenu selectMenuById(Long menuId);

    /**
     * 查询系统正常显示菜单（不含按钮）
     */
    List<SysMenu> selectMenuNormalAll();

    /**
     * 根据用户ID查询菜单
     */
    List<SysMenu> selectMenusByUserId(Long userId);

    /**
     * 根据角色ID查询菜单
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
