package com.kee.ad.service.impl;

import com.kee.ad.dao.OrderDetailDao;
import com.kee.ad.model.OrderDetail;
import com.kee.ad.model.OrderDetailQueryBean;
import com.kee.ad.service.OrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Kee on 2016/11/9.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger logger = LoggerFactory.getLogger(OrderDetailServiceImpl.class);

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public int addOrder(OrderDetail detail) {
        return orderDetailDao.insertSelective(detail);
    }

    @Override
    public List<OrderDetail> listOrderDetails(OrderDetailQueryBean queryBean) {
        return orderDetailDao.listOrderDetails(queryBean);
    }

    @Override
    public int countOrderDetails(OrderDetailQueryBean queryBean) {
        return orderDetailDao.countOrderDetails(queryBean);
    }
}
