package com.ityouzi.mapper;

import com.ityouzi.system.domain.SysRoleDept;

import java.util.List;

public interface SysRoleDeptMapper {

    /**
     * 通过角色ID删除角色和部门关联
     */
    int deleteRoleDeptByRoleId(Long roleId);


    /**
     * 批量新增角色部门信息
     */
    int batchRoleDept(List<SysRoleDept> list);
}
