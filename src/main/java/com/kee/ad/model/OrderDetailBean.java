package com.kee.ad.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author KeeYang on 2017/8/16.
 * @Description :
 */
@ApiModel("预约信息bean")
public class OrderDetailBean extends OrderDetail {

    @ApiModelProperty("经销商联系电话")
    private String connectTel;

    public String getConnectTel() {
        return connectTel;
    }

    public void setConnectTel(String connectTel) {
        this.connectTel = connectTel;
    }
}
