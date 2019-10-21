package com.ityouzi.service.impl;

import com.ityouzi.mapper.SysMenuMapper;
import com.ityouzi.mapper.SysRoleMenuMapper;
import com.ityouzi.redis.annotation.RedisCache;
import com.ityouzi.service.ISysMenuService;
import com.ityouzi.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单 业务层处理
 */
public class SysMenuServiceImpl implements ISysMenuService {

    //权限字符串
    public static final String permission_string = "perms[\"{0}\"]";

    @Autowired
    private SysMenuMapper menuMapper;
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    @RedisCache(key = "user_perms", fieldKey = "#userId")
    public Set<String> selectPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm :perms){
            if (StringUtils.isNotEmpty(perm)){
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
