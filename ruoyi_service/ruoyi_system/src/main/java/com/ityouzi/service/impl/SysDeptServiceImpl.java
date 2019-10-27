package com.ityouzi.service.impl;

import com.ityouzi.annotation.DataScope;
import com.ityouzi.constant.UserConstants;
import com.ityouzi.mapper.SysDeptMapper;
import com.ityouzi.service.SysDeptService;
import com.ityouzi.system.domain.SysDept;
import com.ityouzi.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper deptMapper;


    /**
     * 2019/10/27-17:10
     * 根据部门ID查询信息
     */
    @Override
    public SysDept selectDeptById(Long deptId) {
        return deptMapper.selectDeptById(deptId);
    }

    /**
     * 2019/10/27-17:31
     * 查询部门管理数据
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SysDept> selectDeptList(SysDept sysDept) {
        return deptMapper.selectDeptList(sysDept);
    }

    /**
     * 2019/10/27-17:35
     * 修改保存部门信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDept(SysDept sysDept) {
        SysDept newParentDept = deptMapper.selectDeptById(sysDept.getParentId());
        SysDept oldDept = selectDeptById(sysDept.getDeptId());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept)){
            String newAncestors = newParentDept.getAncestors()+","+newParentDept.getDeptId();
            String oldAncestors = oldDept.getAncestors();
            sysDept.setAncestors(newAncestors);
            updateDeptChildren(sysDept.getDeptId(),newAncestors,oldAncestors);
        }
        int result = deptMapper.updateDept(sysDept);
        if (UserConstants.DEPT_NORMAL.equals(sysDept.getStatus())){
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(sysDept);
        }
        return result;
    }

    /**
     * 2019/10/27-17:57
     * 删除部门管理信息
     */
    @Override
    public int deleteDeptById(Long deptId) {
        return deptMapper.deleteDeptById(deptId);
    }

    /**
     * 2019/10/27-17:58
     * 根据角色ID查询部门编号
     */
    @Override
    public Set<String> roleDeptIds(Long roleId) {
        return deptMapper.selectRoleDeptIds(roleId);
    }


    /**
     * 修改子元素关系
     * @param deptId 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Long deptId, String newAncestors,String oldAncestors)
    {
        List<SysDept> children = deptMapper.selectChildrenDeptById(deptId);
        for (SysDept child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors,newAncestors));
        }
        if (children.size() > 0)
        {
            deptMapper.updateDeptChildren(children);
        }
    }

    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatus(SysDept dept)
    {
        String updateBy = dept.getUpdateBy();
        dept = deptMapper.selectDeptById(dept.getDeptId());
        dept.setUpdateBy(updateBy);
        deptMapper.updateDeptStatus(dept);
    }


}
