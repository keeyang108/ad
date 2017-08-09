package com.kee.ad.pojo;

import org.apache.commons.lang3.StringUtils;

/**
 * @author KeeYang on 2017/8/8.
 * @Description :
 */
public enum StatusEnums {
    NORMAL("正常"),DISABLE("停用"),LOCKED("上锁");

    StatusEnums(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public String getCode(){
        return this.name();
    }

    public static String getDescByCode(String code) {
        String desc = "";
        for (StatusEnums status : StatusEnums.values()) {
            if (StringUtils.isNotBlank(code) && status.getCode().equals(code)) {
                desc = status.getDesc();
                break;
            }
        }

        return desc;
    }

}
