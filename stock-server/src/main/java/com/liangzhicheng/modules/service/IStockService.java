package com.liangzhicheng.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liangzhicheng.modules.entity.Stock;

public interface IStockService extends IService<Stock> {

    /**
     * 根据id获取库存信息
     * @param id
     * @return Stock
     */
    Stock get(Integer id);

    /**
     * 减库存
     * @param goodsId
     * @param orderNum
     */
    void subtract(String goodsId, Integer orderNum);

}
