package com.kee.ad.pojo;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author KeeYang on 2017/8/8.
 * @Description :
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "City")
public class City{

    @XmlAttribute
    private String name;

    @XmlElement(name = "Shop")
    private List<Shop> shops;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", shops=" + shops +
                '}';
    }
}
