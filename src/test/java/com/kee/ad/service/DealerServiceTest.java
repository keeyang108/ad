package com.kee.ad.service;

import com.kee.ad.pojo.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author KeeYang on 2017/8/9.
 * @Description :
 */
public class DealerServiceTest extends BaseSpringTest{

    @Autowired
    private DealerService dealerService;

    @Test
    public void addDealer() throws Exception {

    }

    @Test
    public void updateDealer() throws Exception {

    }

    @Test
    public void deleteDealer() throws Exception {

    }

    @Test
    public void listDealer() throws Exception {

    }

    @Test
    public void convertToXml() throws Exception {
        dealerService.convertToXml();
    }

}
