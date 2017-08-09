package com.kee.ad.dao;

import com.kee.ad.model.Dealer;
import com.kee.ad.model.DealerQueryBean;
import com.kee.ad.pojo.Shop;

import java.util.List;

/**
 * @author KeeYang on 2017/8/8.
 * @Description :
 */
public interface DealerDao extends DealerMapper {

    List<Dealer> selectByCondition(DealerQueryBean queryBean);

    int countByCondition(DealerQueryBean queryBean);

    List<String> selectDistinceProvince();

    List<String> selectCityByProvince(String provinceName);

    List<Shop> selectShopByCity(String cityName);
}
