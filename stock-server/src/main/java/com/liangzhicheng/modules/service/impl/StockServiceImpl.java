package com.liangzhicheng.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liangzhicheng.modules.entity.Stock;
import com.liangzhicheng.modules.mapper.IStockMapper;
import com.liangzhicheng.modules.service.IStockService;
import io.seata.spring.annotation.GlobalLock;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl extends ServiceImpl<IStockMapper, Stock> implements IStockService {

    /**
     * GlobalLock注解，声明事务仅执行在本地RM中，但是本次事务确保在更新状态下的操作记录不会被其他全局事务操作
     * 即将本地事务的执行纳入seata分布式事务的管理，一起竞争全局锁，保证全局事务在执行的时，本地业务不可以操作全局事务中的记录
     */
    @GlobalLock
    @Override
    public Stock get(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void subtract(String goodsId, Integer orderNum) {
        Stock stock = baseMapper.selectOne(
                new LambdaQueryWrapper<Stock>()
                        .eq(Stock::getGoodsId, goodsId)
                        .gt(Stock::getStockNum, 0));
        if(stock != null){
            stock.setStockNum(stock.getStockNum() - orderNum);
            baseMapper.updateById(stock);
        }
    }

}
