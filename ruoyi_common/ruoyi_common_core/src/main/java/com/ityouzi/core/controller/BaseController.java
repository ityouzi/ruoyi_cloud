package com.ityouzi.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ityouzi.constant.Constants;
import com.ityouzi.core.domain.ResultMsg;
import com.ityouzi.core.page.PageDomain;
import com.ityouzi.core.page.TableDataInfo;
import com.ityouzi.core.page.TableSupport;
import com.ityouzi.utils.DateUtils;
import com.ityouzi.utils.ServletUtils;
import com.ityouzi.utils.StringUtils;
import com.ityouzi.utils.sql.SqlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * web层通用数据处理
 */
@Slf4j
public class BaseController {

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        //Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text){
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (pageNum != null && pageSize != null){
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum,pageSize,orderBy);
        }
    }

    /**
     * 获取request
     */
    public HttpServletRequest getRequest(){
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse(){
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession(){
        return getRequest().getSession();
    }

    public long getCurrentUserId(){
        String currentId = getRequest().getHeader(Constants.CURRENT_ID);
        if (StringUtils.isNotBlank(currentId)){
            return Long.valueOf(currentId);
        }
        return 0l;
    }

    public String getLoginName(){
        return getRequest().getHeader(Constants.CURRENT_USERNAME);
    }

    /**
     * 响应请求分页数据
     */
    protected TableDataInfo getDataTable(List<?> list){
        TableDataInfo dataInfo = new TableDataInfo();
        dataInfo.setCode(0);
        dataInfo.setRows(list);
        dataInfo.setTotal(new PageInfo(list).getTotal());
        return dataInfo;
    }


    protected ResultMsg result(List<?> list){
        PageInfo<?> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", list);
        map.put("pageNum", pageInfo.getPageNum());
        map.put("total", pageInfo.getTotal());
        return ResultMsg.ok(map);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected ResultMsg toAjax(int rows)
    {
        return rows > 0 ? ResultMsg.ok() : ResultMsg.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected ResultMsg toAjax(boolean result)
    {
        return result ? ResultMsg.ok() : ResultMsg.error();
    }



}
