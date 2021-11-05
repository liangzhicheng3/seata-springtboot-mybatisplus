package com.liangzhicheng.modules.client;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@Component
public class StockClient {

    @Resource
    private RestTemplate restTemplate;

    public void subtract(String goodsId, Integer orderNum){
        String url = "http://127.0.0.1:8081/api/stock/subtract?goodsId=" + goodsId + "&orderNum=" + orderNum;
        log.warn("[stock client] remote url:{}, subtract ... xid:{}", url, RootContext.getXID());
        try{
            restTemplate.getForEntity(url, Void.class);
        }catch(Exception e){
            log.error("[stock client] remote url:{}, subtract error:{}", url, e.getMessage());
            throw new RuntimeException();
        }
    }

}
