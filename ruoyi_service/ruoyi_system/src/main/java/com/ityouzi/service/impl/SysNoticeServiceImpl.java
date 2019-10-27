package com.ityouzi.service.impl;

import com.ityouzi.core.text.Convert;
import com.ityouzi.mapper.SysNoticeMapper;
import com.ityouzi.service.SysNoticeService;
import com.ityouzi.system.domain.SysNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @date: 2019/10/27-19:02
 * @author: lz
 * 接口描述: 公告 服务层实现
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {

    @Autowired
    private SysNoticeMapper noticeMapper;


    /**
     * 查询公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId) {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增保存通知公告
     *
     * @param notice
     */
    @Override
    public int insertNotice(SysNotice notice) {
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改保存通知公告
     *
     * @param notice
     */
    @Override
    public int updateNotice(SysNotice notice) {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 删除通知公告
     *
     * @param ids
     */
    @Override
    public int deleteNoticeByIds(String ids) {
        return noticeMapper.deleteNoticeByIds(Convert.toStrArray(ids));
    }
}
