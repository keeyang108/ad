package com.kee.ad.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author KeeYang on 2017/7/28.
 * @Description :
 */
@ApiModel("查询基础bean")
public class BaseQueryBean {

    public static int DEFAULT_PAGE_NO = -1;

    private String orderBy;

    @ApiModelProperty("当前页")
    private int pageNo;

    @ApiModelProperty("页条数")
    private int pageSize;

    private int startIndex;

    private int totalCount;

    @ApiModelProperty("是否分页，false则不分页")
    private boolean pagable;

    public BaseQueryBean() {
        this.pageNo = DEFAULT_PAGE_NO;
        this.pageSize = 15;
        this.pagable = true;
    }

    public int getStartIndex() {
        if (this.pageNo == DEFAULT_PAGE_NO) {
            return 0;
        } else {
            if (this.pageNo == 0) {
                this.pageNo = 1;
            }

            return (this.pageNo - 1) * this.pageSize;
        }
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isPagable() {
        return pagable;
    }

    public void setPagable(boolean pagable) {
        this.pagable = pagable;
    }
}
