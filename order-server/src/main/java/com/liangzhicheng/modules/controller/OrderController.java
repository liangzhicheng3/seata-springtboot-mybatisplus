package com.liangzhicheng.modules.controller;

import com.liangzhicheng.modules.entity.Order;
import com.liangzhicheng.modules.service.IOrderService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    @GetMapping(value = "/create")
    public void create(@RequestParam String userId,
                       @RequestParam String goodsId,
                       @RequestParam Integer orderNum){
        log.warn("[order server] create order ... xid:{}", RootContext.getXID());
        orderService.create(userId, goodsId, orderNum);
    }

    @GetMapping(value = "/get/{id}")
    public Order get(@PathVariable("id") Integer id){
        return orderService.get(id);
    }

}
