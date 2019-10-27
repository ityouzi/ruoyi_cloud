package com.ityouzi.service.impl;

import com.ityouzi.mapper.SysMenuMapper;
import com.ityouzi.mapper.SysRoleMenuMapper;
import com.ityouzi.redis.annotation.RedisCache;
import com.ityouzi.service.ISysMenuService;
import com.ityouzi.system.domain.SysMenu;
import com.ityouzi.system.domain.SysUser;
import com.ityouzi.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 菜单 业务层处理
 */
public class SysMenuServiceImpl implements ISysMenuService {

    //权限字符串
    public static final String permission_string = "perms[\"{0}\"]";

    @Autowired
    private SysMenuMapper menuMapper;
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    @RedisCache(key = "user_perms", fieldKey = "#userId")
    public Set<String> selectPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm :perms){
            if (StringUtils.isNotEmpty(perm)){
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 2019/10/27-16:10
     * 根据菜单ID查询信息
     */
    @Override
    public SysMenu selectMenuById(Long menuId) {
        return menuMapper.selectMenuById(menuId);
    }

    /**
     * 2019/10/27-16:18
     * 根据用户查询菜单
     */
    @Override
    public List<SysMenu> selectMenusByUser(SysUser user) {
        List<SysMenu> menus = new LinkedList<SysMenu>();
        // 管理员显示所有菜单信息
        if (user.isAdmin()){
            menus = menuMapper.selectMenuNormalAll();
        }else{
            menus = menuMapper.selectMenusByUserId(user.getUserId());
        }
        return menus;
        // 前端自行构造菜单树
        // return getChildPerms(menus, 0);
    }

    /**
     * 2019/10/27-16:43
     * 根据角色ID查询菜单ID
     */
    @Override
    public List<SysMenu> selectMenuIdsByRoleId(Long roleId) {
        return menuMapper.selectMenuIdsByRoleId(roleId);
    }

    /**
     * 2019/10/27-16:46
     * 查询系统菜单列表
     */
    @Override
    public List<SysMenu> selectMenuList(SysMenu sysMenu) {
        return menuMapper.selectMenuList(sysMenu);
    }

    /**
     * 2019/10/27-16:48
     * 新增保存菜单信息
     */
    @Override
    public int insertMenu(SysMenu menu) {
        return menuMapper.insertMenu(menu);
    }

    /**
     * 2019/10/27-16:51
     * 修改保存菜单信息
     */
    @Override
    public int updateMenu(SysMenu menu) {
        return menuMapper.updateMenu(menu);
    }

    /**
     * 2019/10/27-16:54
     * 删除菜单管理信息
     */
    @Override
    public int deleteMenuById(Long menuId) {
        return menuMapper.deleteMenuById(menuId);
    }
}
