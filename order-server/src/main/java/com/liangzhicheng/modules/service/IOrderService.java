package com.liangzhicheng.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liangzhicheng.modules.entity.Order;

public interface IOrderService extends IService<Order> {

    /**
     * 创建订单，减金额
     * @param userId
     * @param goodsId
     * @param orderNum
     */
    void create(String userId, String goodsId, Integer orderNum);

    /**
     * 根据id获取订单信息
     * @param id
     * @return Order
     */
    Order get(Integer id);

}
