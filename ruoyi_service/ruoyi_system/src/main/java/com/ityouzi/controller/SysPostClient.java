package com.ityouzi.controller;


import com.ityouzi.core.controller.BaseController;
import com.ityouzi.service.SysPostService;
import com.ityouzi.system.domain.SysPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @date: 2019/10/28-10:30
 * @author: lz
 * 描述: 岗位 提供者
 */

@RestController
@RequestMapping("/sys/sysPost")
public class SysPostClient extends BaseController {
    @Autowired
    private SysPostService sysPostService;

    /**
     * 查询岗位
     */
    @GetMapping("get/{postId}")
    public SysPost get(@PathVariable("postId") Long postId) {
        return sysPostService.selectPostById(postId);
    }

    /**
     * 查询岗位列表
     */
    @PostMapping("list")
    public List<SysPost> list(SysPost sysPost) {
        startPage();        //开启分页
        return sysPostService.selectPostList(sysPost);
    }

    /**
     * 新增保存岗位
     */
    @PostMapping("save")
    public int addSave(SysPost sysPost) {
        return sysPostService.insertPost(sysPost);
    }

    /**
     * 修改保存岗位
     */
    @PostMapping("update")
    public int editSave(SysPost sysPost) {
        return sysPostService.updatePost(sysPost);
    }

    /**
     * 删除岗位
     * @throws Exception
     */
    @PostMapping("remove")
    public int remove(String ids) throws Exception {
        return sysPostService.deletePostByIds(ids);
    }

}
