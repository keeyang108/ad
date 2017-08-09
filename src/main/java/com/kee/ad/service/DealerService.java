package com.kee.ad.service;

import com.kee.ad.model.Dealer;
import com.kee.ad.model.DealerQueryBean;
import com.kee.ad.pojo.PageBean;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author KeeYang on 2017/8/8.
 * @Description :
 */
public interface DealerService {

    /**
     * add dealer
     * @param dealer
     * @return
     */
    int addDealer(Dealer dealer);

    /**
     * update by primaryKey
     * @param dealer
     * @return
     */
    int updateDealer(Dealer dealer);

    /**
     * delete by id ;
     * @param id
     * @return
     */
    int deleteDealer(Integer id);

    /**
     * select by condition
     * @param queryBean
     * @return
     */
    PageBean<Dealer> listDealer(DealerQueryBean queryBean);

    /**
     * convert to xml file
     */
    void convertToXml() throws IOException;
}
