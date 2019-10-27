package com.ityouzi.system.domain;


import lombok.Data;

/**
 * 2019/10/27-14:52
 * 角色和菜单关联 sys_role_menu
 */
@Data
public class SysRoleMenu {
    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;
}
