package com.liangzhicheng.modules.client;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Slf4j
@Component
public class AccountClient {

    @Resource
    private RestTemplate restTemplate;

    public void subtract(String userId, BigDecimal orderMoney){
        String url = "http://127.0.0.1:8083/api/account/subtract?userId=" + userId + "&orderMoney=" + orderMoney;
        log.warn("[account client] remote url:{}, subtract ... xid:{}", url, RootContext.getXID());
        try{
            restTemplate.getForEntity(url, Void.class);
        }catch(Exception e){
            log.error("[account client] remote url:{}, subtract error:{}", url, e.getMessage());
            throw new RuntimeException();
        }
    }

}
