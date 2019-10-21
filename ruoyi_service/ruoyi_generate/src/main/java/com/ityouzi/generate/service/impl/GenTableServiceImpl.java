package com.ityouzi.generate.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ityouzi.core.text.Convert;
import com.ityouzi.generate.constant.GenConstants;
import com.ityouzi.generate.domain.GenTable;
import com.ityouzi.generate.domain.GenTableColumn;
import com.ityouzi.generate.mapper.GenTableColumnMapper;
import com.ityouzi.generate.mapper.GenTableMapper;
import com.ityouzi.generate.service.IGenTableService;
import com.ityouzi.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GenTableServiceImpl implements IGenTableService {

    @Autowired
    private GenTableMapper genTableMapper;
    @Autowired
    private GenTableColumnMapper genTableColumnMapper;


    /**
     * 查询业务列表
     *
     * @param genTable 业务信息
     * @return 业务集合
     */
    @Override
    public List<GenTable> selectGenTableList(GenTable genTable) {
        return genTableMapper.selectGenTableList(genTable);
    }

    /**
     * 查询数据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    @Override
    public List<GenTable> selectDbTableList(GenTable genTable) {
        return genTableMapper.selectDbTableList(genTable);
    }

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public GenTable selectGenTableById(Long id) {
        GenTable genTable = genTableMapper.selectGenTableById(id);
        setTableFromOptions(genTable);
        return genTable;
    }

    /**
     * 修改业务
     *
     * @param genTable
     */
    @Override
    public void updateGenTable(GenTable genTable) {
        String options = JSON.toJSONString(genTable.getParams());
        genTable.setOptions(options);
        int row = genTableMapper.updateGenTable(genTable);
        if (row > 0){
            for (GenTableColumn genTableColumn : genTable.getColumns()){
                genTableColumnMapper.updateGenTableColumn(genTableColumn);
            }
        }
    }

    /**
     * 删除业务信息
     *
     * @param ids 需要删除的数据ID
     */
    @Override
    public void deleteGenTableByIds(String ids) {
        genTableMapper.deleteGenTableByIds(Convert.toLongArray(ids));   //string --long
        genTableColumnMapper.deleteGenTableColumnByIds(Convert.toLongArray(ids));
    }

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     * @param operName  操作人员
     */
    @Override
    public void importGenTable(List<GenTable> tableList, String operName) {

        for (GenTable table : tableList){
            String tableName = table.getTableName();

        }

    }

    /**
     * 预览代码
     *
     * @param tableId 表编号
     * @return 预览数据列表
     */
    @Override
    public Map<String, String> previewCode(Long tableId) {
        return null;
    }

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String tableName) {
        return new byte[0];
    }

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String[] tableNames) {
        return new byte[0];
    }

    /**
     * 修改保存参数校验
     *
     * @param genTable 业务信息
     */
    @Override
    public void validateEdit(GenTable genTable) {

    }


    /**
     * 设置代码生成其他选项值
     * @param genTable 设置后的生成对象
     */
    public void setTableFromOptions(GenTable genTable){
        JSONObject parseObject = JSONObject.parseObject(genTable.getOptions());
        if (StringUtils.isNotNull(parseObject)){
            String treeCode = parseObject.getString(GenConstants.TREE_CODE);
            String treeParentCode = parseObject.getString(GenConstants.TREE_PARENT_CODE);
            String treeName = parseObject.getString(GenConstants.TREE_NAME);
            genTable.setTreeCode(treeCode);
            genTable.setTreeParentCode(treeParentCode);
            genTable.setTreeName(treeName);
        }
    }


}
