package com.ityouzi.service.impl;

import com.ityouzi.core.text.Convert;
import com.ityouzi.mapper.SysLogininforMapper;
import com.ityouzi.service.SysLogininforService;
import com.ityouzi.system.domain.SysLogininfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2019/10/28-10:05
 * 服务层处理
 */

@Service
public class SysLogininforServiceImpl implements SysLogininforService {

    @Autowired
    private SysLogininforMapper logininforMapper;


    /**
     * 查询系统登录日志集合
     *
     * @param logininfo 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininfo> selectLogininforList(SysLogininfo logininfo) {
        return logininforMapper.selectLogininforList(logininfo);
    }

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfo logininfor) {
        logininforMapper.insertLogininfor(logininfor);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteLogininforByIds(String ids) {
        return logininforMapper.deleteLogininforByIds(Convert.toStrArray(ids));
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor() {
        logininforMapper.cleanLogininfor();
    }
}
