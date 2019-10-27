package com.ityouzi.service.impl;

import com.ityouzi.annotation.DataScope;
import com.ityouzi.core.text.Convert;
import com.ityouzi.exception.BusinessException;
import com.ityouzi.mapper.SysRoleDeptMapper;
import com.ityouzi.mapper.SysRoleMapper;
import com.ityouzi.mapper.SysRoleMenuMapper;
import com.ityouzi.mapper.SysUserRoleMapper;
import com.ityouzi.service.SysRoleService;
import com.ityouzi.system.domain.SysRole;
import com.ityouzi.system.domain.SysRoleDept;
import com.ityouzi.system.domain.SysRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysRoleDeptMapper roleDeptMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;


    /**
     * 2019/10/27-14:30
     * 通过角色ID查询角色
     */
    @Override
    public SysRole selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 2019/10/27-14:40
     * 根据条件分页查询角色数据
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SysRole> selectRoleList(SysRole sysRole) {
        return roleMapper.selectRoleList(sysRole);
    }

    /**
     * 2019/10/27-14:47
     * 查询所有角色
     */
    @Override
    public List<SysRole> selectRoleAll() {
        return selectRoleList(new SysRole());
    }

    /**
     * 2019/10/27-14:50
     * 新增保存角色信息
     */
    @Override
    @Transactional
    public int insertRole(SysRole role) {
        //新增角色信息
        roleMapper.insertRole(role);
        return insertRoleMenu(role);
    }


    /**
     * 2019/10/27-15:01
     * 修改保存角色信息
     */
    @Override
    @Transactional
    public int updateRole(SysRole role) {

        //修改角色信息
        roleMapper.updateRole(role);
        //删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }

    /**
     * 2019/10/27-15:25
     * 角色状态修改
     */
    @Override
    public int changeStatus(SysRole role) {
        return roleMapper.updateRole(role);
    }

    /**
     * 2019/10/27-15:28
     * 修改数据权限信息
     */
    @Override
    @Transactional
    public int authDataScope(SysRole role) {
        //修改角色信息
        roleMapper.updateRole(role);
        //删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(role.getRoleId());
        // 新增角色和部门信息（数据权限）
        return insertRoleDept(role);
    }

    /**
     * 2019/10/27-15:45
     * 批量删除角色信息
     */
    @Override
    public int deleteRoleByIds(String ids) {
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long roleId : roleIds){
            SysRole role = selectRoleById(roleId);  //查询角色
            if (countUserRoleByRoleId(roleId) > 0){
                throw new BusinessException(String.format("%1$s已分配,不能删除",role.getRoleName()));
            }
        }
        if (roleIds.length>0) return roleMapper.deleteRoleByIds(roleIds);
        return 0;
    }

    /**
     * 2019/10/27-15:47
     * 通过角色ID查询角色使用数量
     */
    @Override
    public int countUserRoleByRoleId(Long roleId){
         return userRoleMapper.countUserRoleByRoleId(roleId);
    }


    /**
     * 2019/10/27-14:51
     * 新增角色菜单信息
     */
    public int insertRoleMenu(SysRole role){
        int rows = 1;
        //新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<>();
        for (Long menuId : role.getMenuIds()){
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0){
            rows = roleMapper.batchRoleMenu(list);
        }
        return rows;
    }

    /**
     * 2019/10/27-15:35
     * 新增角色部门信息(数据权限)
     */
    public int insertRoleDept(SysRole role){
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<SysRoleDept> list = new ArrayList<SysRoleDept>();
        for (Long deptId : role.getDeptIds()){
            SysRoleDept rd = new SysRoleDept();
            rd.setRoleId(role.getRoleId());
            rd.setDeptId(deptId);
            list.add(rd);
        }
        if (list.size() > 0){
            rows = roleDeptMapper.batchRoleDept(list);
        }
        return rows;
    }

}
