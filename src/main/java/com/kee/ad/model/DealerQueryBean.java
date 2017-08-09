package com.kee.ad.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author KeeYang on 2017/8/8.
 * @Description :
 */
@ApiModel("经销商查询bean")
public class DealerQueryBean extends BaseQueryBean{

    @ApiModelProperty("经销商名称")
    private String agencyName;

    @ApiModelProperty("省分")
    private String provinceName;

    @ApiModelProperty("市名")
    private String cityName;

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
