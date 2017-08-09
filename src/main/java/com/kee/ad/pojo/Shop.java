package com.kee.ad.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author KeeYang on 2017/8/8.
 * @Description :
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Shop")
public class Shop{

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Code")
    private String code;

    @XmlElement(name = "Tel")
    private String tel;

    @XmlElement(name = "Address")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
