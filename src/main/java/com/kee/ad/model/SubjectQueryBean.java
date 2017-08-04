package com.kee.ad.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author KeeYang on 2017/7/27.
 * @Description :
 */
@ApiModel("bean for subject query")
public class SubjectQueryBean extends BaseQueryBean {

    @ApiModelProperty("专题状态")
    private Integer status;

    @ApiModelProperty("专题名称")
    private String subjectName;

    @ApiModelProperty("开始时间")
    private Date beginDate;

    @ApiModelProperty("截止时间")
    private Date endDate;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

