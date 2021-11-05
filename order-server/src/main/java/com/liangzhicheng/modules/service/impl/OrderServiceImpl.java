package com.liangzhicheng.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liangzhicheng.modules.client.AccountClient;
import com.liangzhicheng.modules.entity.Order;
import com.liangzhicheng.modules.mapper.IOrderMapper;
import com.liangzhicheng.modules.service.IOrderService;
import io.seata.spring.annotation.GlobalLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class OrderServiceImpl extends ServiceImpl<IOrderMapper, Order> implements IOrderService {

    @Resource
    private AccountClient accountClient;

    @Override
    public void create(String userId, String goodsId, Integer orderNum) {
        BigDecimal orderMoney = new BigDecimal(orderNum).multiply(new BigDecimal(5));
        baseMapper.insert(new Order(userId,  goodsId, orderNum, orderMoney));
        accountClient.subtract(userId, orderMoney);
    }

    @GlobalLock
    @Override
    public Order get(Integer id) {
        return baseMapper.selectById(id);
    }

}
