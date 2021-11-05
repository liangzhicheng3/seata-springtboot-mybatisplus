package com.liangzhicheng.modules.controller;

import com.liangzhicheng.modules.entity.Account;
import com.liangzhicheng.modules.service.IAccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    @Resource
    private IAccountService accountService;

    @GetMapping(value = "/get/{id}")
    public Account get(@PathVariable("id") Integer id){
        return accountService.get(id);
    }

    @GetMapping(value = "/subtract")
    public void subtract(@RequestParam String userId,
                         @RequestParam BigDecimal orderMoney){
        log.warn("[account server] subtract money ... xid:{}", RootContext.getXID());
        accountService.subtract(userId, orderMoney);
    }

}
