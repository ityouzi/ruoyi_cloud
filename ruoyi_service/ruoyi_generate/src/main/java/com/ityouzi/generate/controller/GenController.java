package com.ityouzi.generate.controller;

import com.ityouzi.auth.annotation.HasPermissions;
import com.ityouzi.core.controller.BaseController;
import com.ityouzi.core.domain.R;
import com.ityouzi.core.page.TableDataInfo;
import com.ityouzi.generate.domain.GenTable;
import com.ityouzi.generate.domain.GenTableColumn;
import com.ityouzi.generate.service.IGenTableColumnService;
import com.ityouzi.generate.service.IGenTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 代码生成 操作处理
 */
@RestController
public class GenController extends BaseController {

    @Autowired
    private IGenTableService genTableService;
    @Autowired
    private IGenTableColumnService genTableColumnService;


    /**
     * 查询代码生成列表
     */
    @HasPermissions("tool:gen:list")    //权限
    @GetMapping("/list")
    public TableDataInfo genList(GenTable genTable){
        startPage();                //设置请求分页数据
        List<GenTable> list = genTableService.selectGenTableList(genTable);
        return getDataTable(list);  //响应请求分页数据
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping("/get/{tableId}")
    public R get(@PathVariable("tableId") Long tableId){
        GenTable table = genTableService.selectGenTableById(tableId);
        return R.data(table);
    }

    /**
     * 查询数据库列表
     */
    @HasPermissions("tool:gen:list")
    @GetMapping("/db/list")
    public R dataList(GenTable genTable){
        startPage();
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        return result(list);
    }

    /**
     * 查询数据表字段列表
     */
    @HasPermissions("tool:gen:list")
    @GetMapping("edit")
    public R edit(GenTableColumn genTableColumn){
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(genTableColumn);
        GenTable table = genTableService.selectGenTableById(genTableColumn.getTableId());
        return R.data(table).put("rows",list).put("total",list.size());
    }

}
