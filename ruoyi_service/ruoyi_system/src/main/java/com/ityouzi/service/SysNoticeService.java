package com.ityouzi.service;

import com.ityouzi.system.domain.SysNotice;

import java.util.List;

/**
 * 2019/10/27-19:01
 * 公告 服务层
 */
public interface SysNoticeService {


    /**
     * 查询公告信息
     */
    SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询通知公告列表
     */
    List<SysNotice> selectNoticeList(SysNotice sysNotice);

    /**
     * 新增保存通知公告
     */
    int insertNotice(SysNotice notice);

    /**
     * 修改保存通知公告
     */
    int updateNotice(SysNotice notice);

    /**
     * 删除通知公告
     */
    int deleteNoticeByIds(String ids);
}
