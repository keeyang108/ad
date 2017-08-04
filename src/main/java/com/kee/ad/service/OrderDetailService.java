package com.kee.ad.service;

import com.kee.ad.model.OrderDetail;
import com.kee.ad.model.OrderDetailQueryBean;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by Kee on 2016/11/9.
 */
public interface OrderDetailService {
    /**
     * 添加订单
     * @param detail
     * @return
     */
    int addOrder (OrderDetail detail);

    /**
     * 根据条件查询订单详情
     * @param detail
     * @return
     */
    List<OrderDetail> listOrderDetails(OrderDetailQueryBean detail) throws DataAccessException;

    /**
     * 获取条件查询订单的总数
     * @param detail
     * @return
     */
    int countOrderDetails(OrderDetailQueryBean detail);
}
