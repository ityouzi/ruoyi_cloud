package com.ityouzi.controller;

import com.ityouzi.core.controller.BaseController;
import com.ityouzi.core.page.PageDomain;
import com.ityouzi.service.SysNoticeService;
import com.ityouzi.system.domain.SysNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @date: 2019/10/27-19:00
 * @author: lz
 * 接口描述: 通知公告 提供者
 */
@RestController
@RequestMapping("notice")
public class SysNoticeClient extends BaseController {

    @Autowired
    private SysNoticeService sysNoticeService;


    /**
     * 查询通知公告
     */
    @GetMapping("get/{noticeId}")
    public SysNotice get(@PathVariable("noticeId") Long noticeId) {
        return sysNoticeService.selectNoticeById(noticeId);
    }

    /**
     * 查询通知公告列表
     */
    @PostMapping("list")
    public List<SysNotice> list(SysNotice sysNotice, PageDomain page)
    {
        startPage();
        return sysNoticeService.selectNoticeList(sysNotice);
    }

    /**
     * 新增保存通知公告
     */
    @PostMapping("save")
    public int addSave(SysNotice sysNotice)
    {
        return sysNoticeService.insertNotice(sysNotice);
    }

    /**
     * 修改保存通知公告
     */
    @PostMapping("update")
    public int editSave(SysNotice sysNotice)
    {
        return sysNoticeService.updateNotice(sysNotice);
    }

    /**
     * 删除通知公告
     */
    @PostMapping("remove")
    public int remove(String ids)
    {
        return sysNoticeService.deleteNoticeByIds(ids);
    }
}
