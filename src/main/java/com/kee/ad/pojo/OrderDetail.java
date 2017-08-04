package com.kee.ad.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Kee on 2016/11/8.
 */
public class OrderDetail {

    private Integer id;
    private String name;
    private String sex;
    private String mobile;
    private String province;
    private String city;
    private String agentName;
    private Integer agentCode;
    private String carType;
    private Integer carTypeCode;
    private String mediaName;
    private String terminal;
    private String subject;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private int isActivity;
    private String mediaUrl;

    public OrderDetail() {
    }

    public OrderDetail(String name, String sex, String mobile, String province, String city, String agentName, Integer agentCode, String carType, Integer carTypeCode, String mediaName, String terminal, String subject) {
        this.name = name;
        this.sex = sex;
        this.mobile = mobile;
        this.province = province;
        this.city = city;
        this.agentName = agentName;
        this.agentCode = agentCode;
        this.carType = carType;
        this.carTypeCode = carTypeCode;
        this.mediaName = mediaName;
        this.terminal = terminal;
        this.subject = subject;
    }

    public OrderDetail(String name, String sex, String mobile, String province, String city, String agentName, Integer agentCode, String carType, Integer carTypeCode, String mediaName, String terminal, String subject, int isActivity) {
        this.name = name;
        this.sex = sex;
        this.mobile = mobile;
        this.province = province;
        this.city = city;
        this.agentName = agentName;
        this.agentCode = agentCode;
        this.carType = carType;
        this.carTypeCode = carTypeCode;
        this.mediaName = mediaName;
        this.terminal = terminal;
        this.subject = subject;
        this.isActivity = isActivity;
    }

    public OrderDetail(String name, String sex, String mobile, String province, String city, String agentName, Integer agentCode, String carType, Integer carTypeCode, String mediaName, String terminal, String subject, int isActivity, String mediaUrl) {
        this.name = name;
        this.sex = sex;
        this.mobile = mobile;
        this.province = province;
        this.city = city;
        this.agentName = agentName;
        this.agentCode = agentCode;
        this.carType = carType;
        this.carTypeCode = carTypeCode;
        this.mediaName = mediaName;
        this.terminal = terminal;
        this.subject = subject;
        this.isActivity = isActivity;
        this.mediaUrl = mediaUrl;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSex() {
        return sex;
    }

    public int getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(int isActivity) {
        this.isActivity = isActivity;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Integer getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(Integer agentCode) {
        this.agentCode = agentCode;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Integer getCarTypeCode() {
        return carTypeCode;
    }

    public void setCarTypeCode(Integer carTypeCode) {
        this.carTypeCode = carTypeCode;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", agentName='" + agentName + '\'' +
                ", agentCode=" + agentCode +
                ", carType='" + carType + '\'' +
                ", carTypeCode=" + carTypeCode +
                ", mediaName='" + mediaName + '\'' +
                ", terminal='" + terminal + '\'' +
                ", subject='" + subject + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
