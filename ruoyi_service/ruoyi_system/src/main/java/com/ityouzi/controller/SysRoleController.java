package com.ityouzi.controller;

import com.ityouzi.auth.annotation.HasPermissions;
import com.ityouzi.core.controller.BaseController;
import com.ityouzi.core.domain.ResultMsg;
import com.ityouzi.service.SysRoleService;
import com.ityouzi.system.domain.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @date: 2019/10/27-14:25
 * @author: lz
 * 接口描述: 角色 提供者
 */

@RestController
@RequestMapping("role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 2019/10/27-14:28
     * 查询角色
     */
    @GetMapping("get/{roleId}")
    public SysRole get(@PathVariable("roleId") Long roleId){
        return sysRoleService.selectRoleById(roleId);
    }

    /**
     * 2019/10/27-14:38
     * 查询角色列表
     */
    @GetMapping("list")
    public ResultMsg list(SysRole sysRole){
        startPage();    //开启分页
        return result(sysRoleService.selectRoleList(sysRole));
    }

    @GetMapping("all")
    public ResultMsg all(){
        return ResultMsg.ok().put("rows",sysRoleService.selectRoleAll());
    }

    /**
     * 2019/10/27-14:48
     * 新增保存角色
     */
    @PostMapping("save")
    public ResultMsg addSave(@RequestBody SysRole sysRole){
        return toAjax(sysRoleService.insertRole(sysRole));
    }

    /**
     * 2019/10/27-15:00
     * 修改角色
     */
    @PostMapping("update")
    public ResultMsg editSave(@RequestBody SysRole role){
        return toAjax(sysRoleService.updateRole(role));
    }

    /**
     * 2019/10/27-15:23
     * 修改保存角色
     */
    @PostMapping("status")
    public ResultMsg status(@RequestBody SysRole role){
        return toAjax(sysRoleService.changeStatus(role));
    }

    /**
     * 2019/10/27-15:25
     * 保存角色分配数据权限
     */
    @HasPermissions("system:role:edit")
    @PostMapping("/authDataScope")
    public ResultMsg authDataScopeSave(@RequestBody SysRole role){
        role.setUpdateBy(getLoginName());
        if (sysRoleService.authDataScope(role) > 0){
            return ResultMsg.ok();
        }
        return ResultMsg.error();
    }

    /**
     * 删除角色
     */
    @PostMapping("remove")
    public ResultMsg remove(String ids){
        return toAjax(sysRoleService.deleteRoleByIds(ids));
    }


}
