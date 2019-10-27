package com.ityouzi.controller;

import com.ityouzi.auth.annotation.HasPermissions;
import com.ityouzi.core.controller.BaseController;
import com.ityouzi.core.domain.ResultMsg;
import com.ityouzi.service.SysOperLogService;
import com.ityouzi.system.domain.SysOperLog;
import com.ityouzi.utils.poi.ExcelUtil;
import com.tiyouzi.common.log.annotation.OperLog;
import com.tiyouzi.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @date: 2019/10/27-19:25
 * @author: lz
 * 接口描述: 操作日志记录 提供者
 */
@RestController
@RequestMapping("operLog")
public class SysOperLogController extends BaseController {

    @Autowired
    private SysOperLogService sysOperLogService;

    /**
     * 查询操作日志记录
     */
    @GetMapping("get/{operId}")
    public SysOperLog get(@PathVariable("operId") Long operId) {
        return sysOperLogService.selectOperLogById(operId);
    }

    /**
     * 查询操作日志记录列表
     */
    @HasPermissions("monitor:operlog:list")
    @RequestMapping("list")
    public ResultMsg list(SysOperLog sysOperLog) {
        startPage();    //开启分页
        return result(sysOperLogService.selectOperLogList(sysOperLog));
    }

    /**
     * 导出日志
     */
    @OperLog(title = "操作日志", businessType = BusinessType.EXPORT)
    @HasPermissions("monitor:operlog:export")
    @PostMapping("/export")
    public ResultMsg export(SysOperLog operLog){
        List<SysOperLog> list = sysOperLogService.selectOperLogList(operLog);
        ExcelUtil<SysOperLog> util = new ExcelUtil(SysOperLog.class);
        return util.exportExcel(list,"操作日志");
    }

    /**
     * 新增保存操作日志记录
     */
    @PostMapping("save")
    public void addSave(@RequestBody SysOperLog sysOperLog)
    {
        sysOperLogService.insertOperlog(sysOperLog);
    }

    /**
     * 删除操作日志记录
     */
    @HasPermissions("monitor:operlog:remove")
    @PostMapping("remove")
    public ResultMsg remove(String ids)
    {
        return toAjax(sysOperLogService.deleteOperLogByIds(ids));
    }

    @OperLog(title = "操作日志", businessType = BusinessType.CLEAN)
    @HasPermissions("monitor:operlog:remove")
    @PostMapping("/clean")
    public ResultMsg clean()
    {
        sysOperLogService.cleanOperLog();
        return ResultMsg.ok();
    }



}
