package com.ityouzi.service;

import com.ityouzi.system.domain.SysDept;

import java.util.List;
import java.util.Set;

public interface SysDeptService {

    /**
     * 根据部门ID查询信息
     */
    SysDept selectDeptById(Long deptId);


    /**
     * 查询部门管理数据
     */
    List<SysDept> selectDeptList(SysDept sysDept);

    /**
     * 修改保存部门信息
     */
    int updateDept(SysDept sysDept);

    /**
     * 删除部门管理信息
     */
    int deleteDeptById(Long deptId);

    /**
     * 根据角色ID查询部门编号
     */
    Set<String> roleDeptIds(Long roleId);
}
