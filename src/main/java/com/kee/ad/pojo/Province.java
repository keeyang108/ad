package com.kee.ad.pojo;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author KeeYang on 2017/8/8.
 * @Description :
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Province")
public class Province {

    @XmlAttribute(name = "id")
    private Integer id;

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "City")
    private List<City> cities;

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

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cities=" + cities +
                '}';
    }
}
