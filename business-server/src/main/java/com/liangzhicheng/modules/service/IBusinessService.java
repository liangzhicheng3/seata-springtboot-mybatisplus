package com.liangzhicheng.modules.service;

public interface IBusinessService {

    /**
     * 减库存，下订单，减金额
     * @param userId
     * @param goodsId
     * @param orderNum
     */
    void buy(String userId, String goodsId, Integer orderNum);

}
