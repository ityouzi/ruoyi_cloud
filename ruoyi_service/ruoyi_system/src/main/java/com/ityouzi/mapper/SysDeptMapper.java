package com.ityouzi.mapper;

import com.ityouzi.system.domain.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface SysDeptMapper {

    /**
     * 根据部门ID查询信息
     */
    SysDept selectDeptById(Long deptId);

    /**
     * 查询部门管理数据
     */
    List<SysDept> selectDeptList(SysDept sysDept);

    /**
     * 修改部门信息
     */
    int updateDept(SysDept dept);

    /**
     * 根据ID查询所有子部门
     */
    List<SysDept> selectChildrenDeptById(Long id);

    /**
     * 修改子元素关系
     */
    int updateDeptChildren(@Param("depts") List<SysDept> depts);

    /**
     * 修改所在部门的父级部门状态
     */
    void updateDeptStatus(SysDept dept);

    /**
     * 删除部门管理信息
     */
    int deleteDeptById(Long deptId);

    /**
     * 根据角色ID查询部门编号
     */
    Set<String> selectRoleDeptIds(Long roleId);
}
