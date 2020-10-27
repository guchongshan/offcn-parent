package com.offcn.order.service;

import com.offcn.order.pojo.TOrder;
import com.offcn.order.vo.req.OrderInfoSubmit;

/**
 * @author 胡长生
 * @create 2020-10-26 15:46
 */
public interface OrderService {

    /**
     * 保存订单
     */
    public TOrder saveOrder(OrderInfoSubmit vo);
}
