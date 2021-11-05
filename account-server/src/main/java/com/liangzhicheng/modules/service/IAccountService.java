package com.liangzhicheng.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liangzhicheng.modules.entity.Account;

import java.math.BigDecimal;

public interface IAccountService extends IService<Account> {

    /**
     * 根据id获取账号信息
     * @param id
     * @return Account
     */
    Account get(Integer id);

    /**
     * 减金额
     * @param userId
     * @param orderMoney
     */
    void subtract(String userId, BigDecimal orderMoney);

}
