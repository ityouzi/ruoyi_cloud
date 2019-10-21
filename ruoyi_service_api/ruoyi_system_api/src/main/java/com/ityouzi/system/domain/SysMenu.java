package com.ityouzi.system.domain;

import com.ityouzi.core.domain.BaseEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SysMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 菜单ID */
    private Long              menuId;

    /** 菜单名称 */
    private String            menuName;

    /** 父菜单名称 */
    private String            parentName;

    /** 父菜单ID */
    private Long              parentId;

    /** 打开方式 (_blank新窗口) */
    private String            target;

    /** 显示顺序 */
    private String            orderNum;

    /** 菜单URL */
    private String            url;

    /** 类型:0目录,1菜单,2按钮 */
    private String            menuType;

    /** 菜单URL */
    private String            menuKey;

    /** 菜单URL */
    private String            menuLay;

    /** 菜单状态:0显示,1隐藏 */
    private String            visible;

    /** 权限字符串 */
    private String            perms;

    /** 菜单图标 */
    private String            icon;

    /** 子菜单 */
    private List<SysMenu> children         = new ArrayList<SysMenu>();

}
