package com.ityouzi.controller;


import com.ityouzi.auth.annotation.HasPermissions;
import com.ityouzi.core.controller.BaseController;
import com.ityouzi.core.domain.ResultMsg;
import com.ityouzi.service.SysLogininforService;
import com.ityouzi.system.domain.SysLogininfo;
import com.tiyouzi.common.log.annotation.OperLog;
import com.tiyouzi.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @date: 2019/10/28-10:03
 * @author: lz
 * 描述: 系统访问记录 提供者，用户登录信息控制层
 */
@RestController
@RequestMapping("logininfo")
public class SysLogininforController extends BaseController {

    @Autowired
    private SysLogininforService sysLogininforService;

    /**
     * 查询系统访问记录列表
     */
    @GetMapping("list")
    public ResultMsg list(SysLogininfo sysLogininfo) {
        startPage();
        return result(sysLogininforService.selectLogininforList(sysLogininfo));
    }

    /**
     * 新增保存系统访问记录
     */
    @PostMapping("save")
    public void addSave(@RequestBody SysLogininfo sysLogininfor) {
        sysLogininforService.insertLogininfor(sysLogininfor);
    }

    /**
     * 删除系统访问记录
     */
    @OperLog(title = "访问日志", businessType = BusinessType.DELETE)
    @HasPermissions("monitor:loginlog:remove")
    @PostMapping("remove")
    public ResultMsg remove(String ids) {
        return toAjax(sysLogininforService.deleteLogininforByIds(ids));
    }

    /**
     * 清空日志表
     */
    @OperLog(title = "访问日志", businessType = BusinessType.CLEAN)
    @HasPermissions("monitor:loginlog:remove")
    @PostMapping("/clean")
    public ResultMsg clean()
    {
        sysLogininforService.cleanLogininfor();
        return ResultMsg.ok();
    }
}
