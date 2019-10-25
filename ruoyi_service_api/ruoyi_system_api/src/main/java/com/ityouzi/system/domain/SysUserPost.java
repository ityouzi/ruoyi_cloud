package com.ityouzi.system.domain;

import lombok.Data;

/**
 * 2019/10/25-16:00
 * 用户和岗位关联 sys_user_post
 */
@Data
public class SysUserPost {

    /** 用户ID */
    private Long userId;

    /** 岗位ID */
    private Long postId;
}
