package com.liangzhicheng.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liangzhicheng.modules.entity.Account;
import com.liangzhicheng.modules.mapper.IAccountMapper;
import com.liangzhicheng.modules.service.IAccountService;
import io.seata.spring.annotation.GlobalLock;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl extends ServiceImpl<IAccountMapper, Account> implements IAccountService {

    private static final String ERROR_USER_ID = "1002";

    @GlobalLock
    @Override
    public Account get(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void subtract(String userId, BigDecimal orderMoney) {
        Account account = baseMapper.selectOne(
                new LambdaQueryWrapper<Account>()
                        .eq(Account::getUserId, userId)
                        .gt(Account::getMoney, 0));
        if(account != null){
            account.setMoney(account.getMoney().subtract(orderMoney));
            baseMapper.updateById(account);
        }
        if(ERROR_USER_ID.equals(userId)){
            throw new RuntimeException("[account server] subtract money exception");
        }
    }

}
