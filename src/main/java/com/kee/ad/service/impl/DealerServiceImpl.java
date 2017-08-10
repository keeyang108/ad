package com.kee.ad.service.impl;

import com.kee.ad.dao.DealerDao;
import com.kee.ad.model.Dealer;
import com.kee.ad.model.DealerExample;
import com.kee.ad.model.DealerQueryBean;
import com.kee.ad.pojo.*;
import com.kee.ad.service.DealerService;
import com.kee.ad.util.JAXBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KeeYang on 2017/8/8.
 * @Description :
 */
@Service
public class DealerServiceImpl implements DealerService {

    private final  static Logger log = LoggerFactory.getLogger(DealerServiceImpl.class);

    @Autowired
    private DealerDao dealerDao;

    @Override
    public int addDealer(Dealer dealer) {
        return dealerDao.insertSelective(dealer);
    }

    @Override
    public int updateDealer(Dealer dealer) {
        return dealerDao.updateByPrimaryKeySelective(dealer);
    }

    @Override
    public int deleteDealer(Integer id) {
        return dealerDao.deleteByPrimaryKey(Long.valueOf(id));
    }

    @Override
    public PageBean<Dealer> listDealer(DealerQueryBean queryBean) {
        PageBean<Dealer> pageBean = new PageBean<>(queryBean);
        int count = dealerDao.countByCondition(queryBean);
        if (count > 0){
            pageBean.setTotalCount(count);
            pageBean.setData(dealerDao.selectByCondition(queryBean));
            return pageBean;
        }
        return null;
    }

    @Override
    public void convertToXml() throws IOException {

        List<String> provinceNames = dealerDao.selectDistinceProvince();
        List<Province> provinces = new ArrayList<>();
        for (String provinceName : provinceNames){
            Province province = new Province();
            province.setName(provinceName);
            List<String> cityNames = dealerDao.selectCityByProvince(provinceName);
            List<City> cities = new ArrayList<>();
            for (String cityName : cityNames){
                City city = new City();
                city.setName(cityName);
                List<Shop> shops = dealerDao.selectShopByCity(cityName);
                city.setShops(shops);
                cities.add(city);
            }
            province.setCities(cities);
            provinces.add(province);
        }
        Agency agency = new Agency();
        agency.setProvinces(provinces);
        String xml = JAXBUtil.convertToXml(agency);
        ByteArrayInputStream stream = new ByteArrayInputStream(xml.getBytes());
        File file = new File("5x.xml");
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        stream.close();

        log.info("xml = {}",xml);
    }

    @Override
    public void addDealerFromExcel(List<List<String>> lists) {
        dealerDao.deleteByExample(new DealerExample());
        for (List<String> list : lists){
            Dealer dealer = new Dealer();
            dealer.setProvinceName(list.get(0));
            dealer.setCityName(list.get(1));
            dealer.setAgencyName(list.get(2));
            dealer.setConnectTel(list.get(3));
            dealer.setAgencyAddress(list.get(4));
            dealerDao.insertSelective(dealer);
        }
    }
}
