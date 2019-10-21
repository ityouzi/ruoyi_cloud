package com.ityouzi.mapper;

import java.util.List;

/**
 * 菜单表 数据层
 */
public interface SysMenuMapper {


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<String> selectPermsByUserId(Long userId);



}
