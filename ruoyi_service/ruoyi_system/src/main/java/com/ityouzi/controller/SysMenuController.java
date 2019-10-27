package com.ityouzi.controller;

import com.ityouzi.annotation.LoginUser;
import com.ityouzi.auth.annotation.HasPermissions;
import com.ityouzi.core.controller.BaseController;
import com.ityouzi.core.domain.ResultMsg;
import com.ityouzi.service.ISysMenuService;
import com.ityouzi.system.domain.SysMenu;
import com.ityouzi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @date: 2019/10/27-16:06
 * @author: lz
 * 接口描述: 菜单权限
 */
@RestController
@RequestMapping("menu")
public class SysMenuController extends BaseController {

    @Autowired
    private ISysMenuService sysMenuService;


    /**
     * 查询菜单权限
     */
    @GetMapping("get/{menuId}")
    public SysMenu get(@PathVariable("menuId") Long menuId)
    {
        return sysMenuService.selectMenuById(menuId);
    }

    /**
     * 根据uid查权限
     */
    @GetMapping("perms/{userId}")
    public Set<String> perms(@PathVariable("userId") Long userId){
        return sysMenuService.selectPermsByUserId(userId);
    }

    /**
     * 查询菜单权限
     */
    @GetMapping("user")
    public List<SysMenu> user(@LoginUser SysUser sysUser)
    {
        return sysMenuService.selectMenusByUser(sysUser);
    }

    /**
     * 2019/10/27-16:28
     * 根据角色编号查询菜单编号（用于勾选）
     */
    @GetMapping("role/{roleId}")
    public List<SysMenu> role(@PathVariable("roleId") Long roleId)
    {
        if (null == roleId || roleId <= 0){
            return null;
        }
        return sysMenuService.selectMenuIdsByRoleId(roleId);
    }

    /**
     * 查询菜单权限列表
     */
    @HasPermissions("system:menu:view")
    @GetMapping("list")
    public ResultMsg list(SysMenu sysMenu) {
        return result(sysMenuService.selectMenuList(sysMenu));
    }

    /**
     * 新增保存菜单权限
     */
    @PostMapping("save")
    public ResultMsg addSave(@RequestBody SysMenu sysMenu) {
        return toAjax(sysMenuService.insertMenu(sysMenu));
    }

    /**
     * 修改保存菜单权限
     */
    @PostMapping("update")
    public ResultMsg editSave(@RequestBody SysMenu sysMenu) {
        return toAjax(sysMenuService.updateMenu(sysMenu));
    }

    /**
     * 删除菜单权限
     */
//    @OperLog(title = "菜单管理", businessType = BusinessType.DELETE)
    @PostMapping("remove/{menuId}")
    public ResultMsg remove(@PathVariable("menuId") Long menuId) {
        return toAjax(sysMenuService.deleteMenuById(menuId));
    }

}
