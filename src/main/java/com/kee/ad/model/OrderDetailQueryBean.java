package com.kee.ad.model;

import java.util.Date;

/**
 * Created by Kee on 2017/3/27.
 */
public class OrderDetailQueryBean extends BaseQueryBean {

    private String subject;

    private Date startDate;

    private Date endDate;

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
