package com.kee.ad.dao;

import com.kee.ad.model.OrderDetail;
import com.kee.ad.model.OrderDetailQueryBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

/**
 * Created by Kee on 2016/11/8.
 */
public interface OrderDetailDao extends OrderDetailMapper {


    /**
     * 根据条件查询订单详情
     * @param queryBean
     * @return
     */
    List<OrderDetail> listOrderDetails(@Param("queryBean") OrderDetailQueryBean queryBean) throws DataAccessException;

    Map<String, Object> countOrderDetails(@Param("queryBean") OrderDetailQueryBean queryBean);

}
