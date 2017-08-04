package com.kee.ad.pojo;

import com.kee.ad.model.BaseQueryBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Kee on 2016/11/15.
 */
public class PageBean<T> {

    private Integer currPage;
    private Integer pageSize;
    private Integer totalPage;
    private List<T> data;
    private Integer totalCount;
    private Map<String, Object> conditions;

    public PageBean() {
    }

    public PageBean(BaseQueryBean baseQueryBean) {
        this.currPage = baseQueryBean.getPageNo();
        this.totalCount = 0;
        this.pageSize = 15;
        this.pageSize = baseQueryBean.getPageSize();
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Map<String, Object> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, Object> conditions) {
        this.conditions = conditions;
    }
}
