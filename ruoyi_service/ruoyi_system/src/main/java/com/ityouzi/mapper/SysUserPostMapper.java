package com.ityouzi.mapper;

import com.ityouzi.system.domain.SysUserPost;

import java.util.List;

public interface SysUserPostMapper {

    /**
     * 批量新增用户岗位信息
     */
    int batchUserPost(List<SysUserPost> userPostList);

    /**
     * 通过用户ID删除用户和岗位关联
     */
    int deleteUserPostByUserId(Long userId);
}
