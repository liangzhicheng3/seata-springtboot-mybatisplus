package com.liangzhicheng.modules.service.impl;

import com.liangzhicheng.modules.client.OrderClient;
import com.liangzhicheng.modules.client.StockClient;
import com.liangzhicheng.modules.service.IBusinessService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class BusinessServiceImpl implements IBusinessService {

    @Resource
    private OrderClient orderClient;
    @Resource
    private StockClient stockClient;

    @GlobalTransactional
    @Override
    public void buy(String userId, String goodsId, Integer orderNum) {
        log.warn("[business server] buy order ... xid:{}", RootContext.getXID());
        stockClient.subtract(goodsId, orderNum);
        orderClient.create(userId, goodsId, orderNum);
    }

}
