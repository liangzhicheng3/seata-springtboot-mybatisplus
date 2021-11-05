package com.liangzhicheng.modules.controller;

import com.liangzhicheng.modules.entity.Stock;
import com.liangzhicheng.modules.service.IStockService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "/api/stock")
public class StockController {

    @Resource
    private IStockService stockService;

    @GetMapping(value = "/get/{id}")
    public Stock get(@PathVariable("id") Integer id){
        return stockService.get(id);
    }

    @GetMapping(value = "/subtract")
    public void subtract(@RequestParam String goodsId,
                         @RequestParam Integer orderNum){
        log.warn("[stock server] subtract stock ... xid:{}", RootContext.getXID());
        stockService.subtract(goodsId, orderNum);
    }

}
