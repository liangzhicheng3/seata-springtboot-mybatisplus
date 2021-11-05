package com.liangzhicheng.modules.client;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@Component
public class OrderClient {

    @Resource
    private RestTemplate restTemplate;

    public void create(String userId, String goodsId, Integer orderNum){
        String url = "http://127.0.0.1:8082/api/order/create?userId=" + userId + "&goodsId=" + goodsId + "&orderNum=" + orderNum;
        log.warn("[order client] remote url:{}, create ... xid:{}", url, RootContext.getXID());
        try{
            restTemplate.getForEntity(url, Void.class);
        }catch(Exception e){
            log.error("[order client] remote url:{}, create error:{}", url, e.getMessage());
            throw new RuntimeException();
        }
    }

}
