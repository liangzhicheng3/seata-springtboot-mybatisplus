package com.liangzhicheng.modules.controller;

import com.liangzhicheng.modules.service.IBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "/api/business")
public class BusinessController {

    @Resource
    private IBusinessService businessService;

    /**
     * 下单后模拟全局事务提交
     * @return
     */
    @RequestMapping(value = "/buy/commit")
    public Boolean buyCommit(){
        businessService.buy("1001", "2001", 1);
        return true;
    }

    /**
     * 下单后模拟全局事务提交
     * @return
     */
    @RequestMapping(value = "/buy/rollback")
    public Boolean buyRollback(){
        try{
            businessService.buy("1002", "2001", 1);
        }catch(Exception e){
            log.error("rollback error:{}", e.getMessage());
            return false;
        }
        return true;
    }

}
