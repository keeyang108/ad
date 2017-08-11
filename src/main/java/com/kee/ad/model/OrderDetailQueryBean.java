package com.kee.ad.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Kee on 2017/3/27.
 */
@ApiModel("预约信息查询bean")
public class OrderDetailQueryBean extends BaseQueryBean {

    @ApiModelProperty("专题名称")
    private String subject;

    @ApiModelProperty("开始时间")
    private Date startDate;

    @ApiModelProperty("结束时间")
    private Date endDate;

    @ApiModelProperty("是否活动专题")
    private boolean isActivity;

    public boolean getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(boolean activity) {
        isActivity = activity;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "OrderDetailQueryBean{" +
                "subject='" + subject + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActivity=" + isActivity +
                '}';
    }
}
