package com.kee.ad.pojo;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author KeeYang on 2017/8/8.
 * @Description :
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "list")
public class Agency {
    @XmlElement(name = "Province")
    private List<Province> provinces;

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }
}

