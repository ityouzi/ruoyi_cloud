package com.ityouzi.controller;

import com.ityouzi.auth.annotation.HasPermissions;
import com.ityouzi.core.controller.BaseController;
import com.ityouzi.core.domain.ResultMsg;
import com.ityouzi.service.DistrictsService;
import com.ityouzi.system.domain.Districts;
import com.ityouzi.utils.poi.ExcelUtil;
import com.tiyouzi.common.log.annotation.OperLog;
import com.tiyouzi.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @date: 2019/10/28-11:08
 * @author: lz
 * 描述: 地区 信息操作处理
 */

@RestController
@RequestMapping("districts")
public class SysDistrictsController extends BaseController {

    @Autowired
    private DistrictsService districtsService;

    /**
     * 查询地区列表
     */
    @HasPermissions("system:districts:list")
    @RequestMapping("/list")
    public ResultMsg list(Districts districts) {
        startPage();
        return result(districtsService.selectDistrictsList(districts));
    }


    /**
     * 导出地区列表
     */
    @HasPermissions("system:districts:export")
    @OperLog(title = "地区", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResultMsg export(Districts districts) {
        List<Districts> list = districtsService.selectDistrictsList(districts);
        ExcelUtil<Districts> util = new ExcelUtil<Districts>(Districts.class);
        return util.exportExcel(list, "districts");
    }

    /**
     * 新增保存地区
     */
    @HasPermissions("system:districts:add")
    @OperLog(title = "地区", businessType = BusinessType.INSERT)
    @PostMapping("save")
    public ResultMsg addSave(@RequestBody Districts districts)
    {
        districts.setPid(districts.getId() / 100);
        districts.setCreateTime(new Date());
        districts.setUpdateTime(new Date());
        districts.setOperator(getLoginName());
        return toAjax(districtsService.insertDistricts(districts));
    }


    /**
     * 修改保存地区
     */
    @HasPermissions("system:districts:edit")
    @OperLog(title = "地区", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    public ResultMsg editSave(@RequestBody Districts districts)
    {
        districts.setPid(districts.getId() / 100);
        districts.setOperator(getLoginName());
        districts.setUpdateTime(new Date());
        return toAjax(districtsService.updateDistricts(districts));
    }


    /**
     * 删除地区
     */
    @HasPermissions("system:districts:remove")
    @OperLog(title = "地区", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public ResultMsg remove(String ids)
    {
        return toAjax(districtsService.deleteDistrictsByIds(ids));
    }

}
